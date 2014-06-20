package org.bbaw.bts.ui.main.parts;

import java.util.List;

import javax.inject.Inject;
import javax.annotation.PostConstruct;

import org.bbaw.bts.btsmodel.BTSDBBaseObject;
import org.bbaw.bts.btsviewmodel.BtsviewmodelFactory;
import org.bbaw.bts.btsviewmodel.TreeNodeWrapper;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;

import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.SashForm;

public class ComparePart extends AbstractComparePart {
	private TableViewer leftTableViewer;
	private TableViewer rightTableViewer;
	private Menu rightContextMenu;
	private ISelectionChangedListener rightSelectionListener;
	protected BTSDBBaseObject selectedLeftVersion;

	@Inject
	public ComparePart() {
		// TODO Your code here
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 0;
		gl_parent.marginWidth = 0;
		gl_parent.horizontalSpacing = 0;
		parent.setLayout(gl_parent);

		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblSelectVersion = new Label(composite, SWT.NONE);
		lblSelectVersion.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true,
				false, 1, 1));
		lblSelectVersion.setText("Select Master Version");

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false,
				1, 1));
		lblNewLabel.setText("Select Compare Version");

		leftTableViewer = new TableViewer(composite, SWT.BORDER | SWT.V_SCROLL);
		Table table = leftTableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(
				adapterFactory);
		AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(
				adapterFactory);
		leftTableViewer.setContentProvider(contentProvider);
		leftTableViewer.setLabelProvider(labelProvider);
		leftSelectionListener = new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection) event
						.getSelection();
				if (selection.getFirstElement() instanceof TreeNodeWrapper) {
					TreeNodeWrapper tn = (TreeNodeWrapper) selection
							.getFirstElement();
					selectedLeftVersion = (BTSDBBaseObject) tn.getObject();
					loadRightVersion(selectedLeftVersion);
				}
			}
		};
		leftTableViewer.addSelectionChangedListener(leftSelectionListener);

		leftContextMenu = new Menu(leftTableViewer.getTable());
		leftTableViewer.getTable().setMenu(leftContextMenu);

		rightTableViewer = new TableViewer(composite, SWT.BORDER | SWT.V_SCROLL);
		Table rtable = rightTableViewer.getTable();
		rtable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		AdapterFactoryLabelProvider rightlabelProvider = new AdapterFactoryLabelProvider(
				adapterFactory);
		AdapterFactoryContentProvider rightContentProvider = new AdapterFactoryContentProvider(
				adapterFactory);
		rightTableViewer.setContentProvider(rightContentProvider);
		rightTableViewer.setLabelProvider(rightlabelProvider);

		rightSelectionListener = new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection) event
						.getSelection();
				if (selection.getFirstElement() instanceof TreeNodeWrapper) {
					TreeNodeWrapper tn = (TreeNodeWrapper) selection
							.getFirstElement();
					selectedRightVersion = (BTSDBBaseObject) tn.getObject();
					loadRightVersion(selectedRightVersion);
				}
			}
		};
		rightTableViewer.addSelectionChangedListener(rightSelectionListener);

		rightContextMenu = new Menu(rightTableViewer.getTable());
		rightTableViewer.getTable().setMenu(rightContextMenu);
		rightContextMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuHidden(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuShown(MenuEvent e) {

			}
		});
		fillListContextMenu(rightContextMenu);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));

		tabFolder = new CTabFolder(composite_1, SWT.BORDER);
		sashForm.setWeights(new int[] { 1, 1 });
		
		leftContextMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuHidden(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuShown(MenuEvent e) {

			}
		});
		fillListContextMenu(leftContextMenu);

		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		loadInput();

		tabFolder.setSelection(0);
	}

	protected void loadInput() {

		loadAvailableRevision();
		loadCompareViewers();
	}

	private void loadAvailableRevision() {
		List<BTSDBBaseObject> conflictObjects = compareObjectsController
				.listAvailableVersions(object, true);
		compareRevInput = BtsviewmodelFactory.eINSTANCE.createTreeNodeWrapper();
		compareRevInput.getChildren().addAll(loadNodes(conflictObjects));
		rightTableViewer.setInput(compareRevInput);

	}

	@Focus
	public void onFocus() {
		// TODO Your code here
	}

	@Persist
	public void save() {
		// TODO Your code here
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}
}