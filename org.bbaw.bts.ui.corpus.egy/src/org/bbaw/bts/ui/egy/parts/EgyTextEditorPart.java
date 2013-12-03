package org.bbaw.bts.ui.egy.parts;

import grammaticalBase.model.text.ElementOccurrence;
import grammaticalBase.model.text.TextModel;
import grammaticalBase.model.text.WordOccurrence;
import grammaticalBase.textEditor.view.textView.JTextAsWordsEditorPanel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import jsesh.editor.JMDCEditor;

import org.bbaw.bts.btsmodel.BTSCorpusObject;
import org.bbaw.bts.btsmodel.BTSObject;
import org.bbaw.bts.btsmodel.BTSSentenceItem;
import org.bbaw.bts.btsmodel.BTSText;
import org.bbaw.bts.core.corpus.controller.partController.BTSTextEditorController;
import org.bbaw.bts.ui.egy.textSign.TextSignEditorComposite;
import org.bbaw.bts.ui.font.BTSFontManager;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;

public class EgyTextEditorPart
{

	@Inject
	private BTSFontManager fontManager;

	@Inject
	private BTSTextEditorController textEditorController;
	@Inject
	private ESelectionService selectionService;
	@Inject
	private UISynchronize sync;
	@Inject
	private EventBroker eventBroker;
	private static final String FONT_NAME = "FreeSans";
	private static final int SIZE = 10;

	private StyledText styledText;
	private BTSText text;
	private Widget shell;
	private CTabItem tbtmBtseditor;
	private CTabFolder tabFolder;
	private StyledText plainTextEditor;
	private JTextAsWordsEditorPanel editorPanel;
	private Text codeBufferText;
	private JMDCEditor jseshEditor;

	protected int counter;

	protected boolean loading;

	private VerticalRuler verticalRuler;

	private ProjectionViewer textViewer;

	private Document document;

	private IAnnotationModel annotationModel;

	protected int tabSelection;

	private TextModel textModel;

	private Map<Object, BTSSentenceItem> ramsesTextModelMap;

	private TextSignEditorComposite signTextEditor;

	/**
	 * @param parent
	 */
	@PostConstruct
	public void createComposite(Composite parent)
	{
		parent.setLayout(new GridLayout());
		shell = new Shell();

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new GridLayout());
		{
			tabFolder = new CTabFolder(composite, SWT.BORDER | SWT.BOTTOM);
			tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
			tabFolder.setLayout(new GridLayout());
			tabFolder.addSelectionListener(new SelectionAdapter()
			{

				@Override
				public void widgetSelected(SelectionEvent e)
				{
					int oldSelection = tabSelection;
					tabSelection = tabFolder.getSelectionIndex();
					if (tabSelection == oldSelection)
					{
						return;
					} else
					{
						//update model from old selection editor
						switch (oldSelection)
						{
							case 0:
							{
								updateModelFromTranscription();
								break;
							}
							case 1:
							{
								updateModelFromSignText();
								break;
							}
							case 2:
							{
								updateModelFromRamses();
								break;
							}
							case 3:
							{
								updateModelFromJSesh();
								break;
							}
						}

						// load updated model into selected editor
						switch (tabSelection)
						{
							case 0:
							{
								loadInputTranscription(text);
								break;
							}
							case 1:
							{
								loadInputSignText(text);
								break;
							}
							case 2:
							{
								loadInputRamses(text);
								break;
							}
							case 3:
							{
								loadInputJSesh(text);
								break;
							}
						}
					}

				}
			});

			tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
					SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
			{
				//				CTabItem tbtm0 = new CTabItem(tabFolder, SWT.NONE);
				//				tbtm0.setText("WrappedSourceViewer");
				//				{
				//					Composite plainTextComp = new Composite(tabFolder, SWT.NONE | SWT.BORDER);
				//					plainTextComp.setLayout(new GridLayout(2, false));
				//					tbtm0.setControl(plainTextComp);
				//
				//					WrappedSourceViewer wsv = new WrappedSourceViewer(plainTextComp);
				//					plainTextComp.layout();
				//					plainTextComp.pack();
				//				}
				CTabItem edTab = new CTabItem(tabFolder, SWT.NONE);
				edTab.setText("Transcription");

				{
					Composite btsEditorComp = new Composite(tabFolder, SWT.BORDER);
					btsEditorComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
					btsEditorComp.setLayout(new FillLayout());
					//					((FillLayout) btsEditorComp.getLayout()).
					edTab.setControl(btsEditorComp);
					IEditedResourceProvider resourceProvider = new IEditedResourceProvider()
					{

						@Override
						public XtextResource createResource()
						{
							try
							{
								MyDslQueryStandaloneSetup.doSetup();
								ResourceSet resourceSet = new ResourceSetImpl();
								Resource resource = resourceSet.createResource(URI.createURI("somefile.MyDsl"));

								return (XtextResource) resource;
							} catch (Exception e)
							{
								return null;
							}
						}
					};

					MyDslActivator activator = MyDslActivator.getInstance();
					Injector injector = activator.getInjector(MyDslActivator.QUERYNAME);
					EmbeddedEditorFactory factory = injector.getInstance(EmbeddedEditorFactory.class);
					handle = factory.newEditor(resourceProvider).withParent(parent);

					// keep the partialEditor as instance var to read / write the edited text
					partialEditor = handle.createPartialEditor(true);

				}

			}

		}
		tabFolder.setSelection(0);
		composite.layout();
		composite.pack();
	}

	protected void updateModelFromSignText()
	{
		// TODO Auto-generated method stub

	}

	protected void updateModelFromJSesh()
	{
		// TODO Auto-generated method stub

	}

	private void notifyRamsesWordSelection(final List<ElementOccurrence> selectedElements)
	{
		sync.asyncExec(new Runnable()
		{
			private ElementOccurrence selection;

			public void run()
			{
				if (selectedElements.size() == 1)
				{
					if (selection == null || !selection.equals(selectedElements.get(0)))
					{
						selection = selectedElements.get(0);
						System.out.println("EgyptEditor notify RamsesWordSelection " + selection);

						selectionService.setSelection(selection);
						eventBroker.send("egywordSelection", selection);
					}
				} else if (editorPanel.getCurrentWord() != null)
				{
					if (selection == null || !selection.equals(selectedElements.get(0)))
					{
						selection = editorPanel.getCurrentWord();
						System.out.println("EgyptEditor notify RamsesWordSelection " + selection);
						selectionService.setSelection(selection);
						eventBroker.send("egywordSelection", selection);
					}
				}
			}
		});
	}

	protected void updateModelFromRamses()
	{
		this.text = textEditorController.updateTextFromRamsesModel(text, textModel, ramsesTextModelMap);

	}

	protected void updateModelFromTranscription()
	{
		this.text = textEditorController.updateTextFromDocument(text, document, annotationModel, textViewer);

	}

	protected void loadInputJSesh(BTSText text2)
	{
		// TODO Auto-generated method stub

	}

	protected void loadInputRamses(BTSText text2)
	{

		if (ramsesTextModelMap == null)
		{
			ramsesTextModelMap = new HashMap<Object, BTSSentenceItem>();
		}
		textEditorController.transformToRamsesTextModel(text, textModel, ramsesTextModelMap);
		editorPanel.clear();
		editorPanel.setTextModel(textModel);

	}

	protected void loadInputTranscription(BTSText text)
	{
		Document doc = new Document();
		this.document = doc;

		IAnnotationModel model = new AnnotationModel();
		this.annotationModel = model;
		textEditorController.transformToDocument(text, doc, model);
		textViewer.setDocument(doc, model);
		verticalRuler.setModel(model);

	}

	@Inject
	@Optional
	void eventReceivedNew(@EventTopic("model_new/BTSWord*") Object object)
	{
		if (object instanceof WordOccurrence)
		{
			WordOccurrence word = (WordOccurrence) object;
			ElementOccurrence current = editorPanel.getCurrentWord();
			int index = textModel.indexOf(current);
			editorPanel.insert(word);
			editorPanel.setCursorPosition(index + 1);

		}
	}

	@Inject
	void setSelection(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) BTSObject selection)
	{
		if (selection == null)
		{
			/* implementation not shown */
		} else
		{
			if (selection instanceof BTSText)
			{
				loadInput((BTSCorpusObject) selection);
			}
		}
	}

	private void loadInput(BTSCorpusObject o)
	{
		if (o instanceof BTSText)
		{
			if (!o.equals(text))
			{
				purgeCache();
			}
			this.text = (BTSText) o;

			switch (tabFolder.getSelectionIndex())
			{
				case 0:
				{
					loadInputTranscription(text);
					break;
				}
				case 1:
				{
					loadInputSignText(text);
					break;
				}
				case 2:
				{
					loadInputRamses(text);
					break;
				}
				case 3:
				{
					loadInputJSesh(text);
					break;
				}
			}

			//			plainTextEditor.setText(((BTSText) o).getCode());
		} else
		{
			// plainTextEditor.setText("");
		}

	}

	private void loadInputSignText(BTSText text2)
	{
		signTextEditor.setInput(text2);

	}

	private void purgeCache()
	{
		textModel = new TextModel();

	}

	@Focus
	public void setFocus()
	{
		tabFolder.setFocus();
	}

	public boolean save()
	{
		switch (tabFolder.getSelectionIndex())
		{
			case 0:
			{
				updateModelFromTranscription();
				break;
			}
			case 1:
			{
				updateModelFromRamses();
				break;
			}
			case 2:
			{
				updateModelFromJSesh();
				break;
			}
		}
		return textEditorController.save(this.text);
	}

	public JTextAsWordsEditorPanel getRamsesTextEditorPanel()
	{
		return editorPanel;
	}
}
