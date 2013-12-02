package org.bbaw.bts.app.login.internal;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;

import org.bbaw.bts.btsmodel.BTSUser;
import org.bbaw.bts.commons.BTSConstants;
import org.bbaw.bts.core.commons.BTSUIConstants;
import org.bbaw.bts.core.services.BTSUserService;
import org.bbaw.bts.modelUtils.StringEncryption;
import org.bbaw.bts.searchModel.BTSQueryRequest;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.elasticsearch.index.query.QueryBuilders;

public class LoginDialog extends Dialog
{

	protected Composite loginComposite;

	private final Shell shell;

	private Image titleImage;

	private ImageDescriptor imageDescriptor;

	private IEclipseContext context;

	private BTSUserService uService;

	private Text passwortText;

	private Text userText;

	private Button okButton;

	private Button cancelButton;

	private BTSUser validUser;

	private Label errorLabel;

	public LoginDialog(Shell parentShell, IEclipseContext context, BTSUserService uService)
	{
		super(parentShell);
		this.shell = parentShell;
		this.context = context;
		this.uService = uService;
	}

	@Override
	protected void configureShell(Shell shell)
	{
		super.configureShell(shell);
		shell.setText("BTS Login");
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);
		Composite control = createContentArea(parent);
		control.setData("org.eclipse.e4.ui.css.id", "LoginDialog");
		Rectangle controlRect = control.getBounds();

		// looks strange in multi monitor environments
		// Rectangle displayBounds = shell.getDisplay().getBounds();

		Monitor primary = shell.getDisplay().getPrimaryMonitor();
		Rectangle displayBounds = primary.getBounds();

		int x = (displayBounds.width - controlRect.width) / 2;
		int y = (displayBounds.height - controlRect.height) / 2;
		shell.setBounds(x, y, controlRect.width, controlRect.height);

		return control;
	}

	protected Composite createContentArea(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		composite.setLayout(gridLayout);

		if (imageDescriptor == null)
		{
			imageDescriptor = imageDescriptorFromURI(URI
					.createURI("platform:/plugin/org.bbaw.bts.app.login/images/btsStart.jpg"));
		}
		if (imageDescriptor != null)
		{
			titleImage = imageDescriptor.createImage();
			Label imageLabel = new Label(composite, SWT.NONE);

			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.verticalAlignment = GridData.BEGINNING;
			data.horizontalSpan = 1;
			imageLabel.setLayoutData(data);
			imageLabel.setImage(titleImage);
		}

		Composite userPasswortComposite = new Composite(composite, SWT.NONE);

		errorLabel = new Label(userPasswortComposite, SWT.BOLD);
		errorLabel.setText("");
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = GridData.FILL;
		gd.horizontalSpan = 2;
		errorLabel.setLayoutData(gd);
		errorLabel.setForeground(BTSUIConstants.VIEW_FOREGROUND_INVALID_COLOR);

		userPasswortComposite.setData("org.eclipse.e4.ui.css.id", "LoginDialog");
		GridLayout gridLayout2 = new GridLayout(2, false);
		gridLayout2.marginHeight = 10;
		gridLayout2.marginWidth = 30;
		userPasswortComposite.setLayout(gridLayout2);
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		userPasswortComposite.setLayoutData(gridData);

		Label userLabel = new Label(userPasswortComposite, SWT.RIGHT);
		userLabel.setText("User  ");
		userText = new Text(userPasswortComposite, SWT.BORDER);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		userText.setLayoutData(gridData);
		userText.addModifyListener(new ModifyListener()
		{

			@Override
			public void modifyText(ModifyEvent e)
			{
				errorLabel.setText("");

			}
		});

		Label passwordLabel = new Label(userPasswortComposite, SWT.RIGHT);
		passwordLabel.setText("Password  ");
		passwortText = new Text(userPasswortComposite, SWT.PASSWORD | SWT.BORDER);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		passwortText.setLayoutData(gridData);
		passwortText.addModifyListener(new ModifyListener()
		{

			@Override
			public void modifyText(ModifyEvent e)
			{
				errorLabel.setText("");

			}
		});

		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		// create OK and Cancel buttons by default
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);

		// FIXME add custom buttons
		// add authentication validation
		// set return value according to authentication result.
		// Bundle bundle = FrameworkUtil.getBundle(getClass());
		// BundleContext bundleContext = bundle.getBundleContext();
		// IEclipseContext eclipseCtx =
		// EclipseContextFactory.getServiceContext(bundleContext);
		// eclipseCtx.set("currentUser", user);
		okButton = createOkButton(parent, IDialogConstants.OK_ID, "Login", true);
		cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, "Cancel", false);
	}

	private Button createOkButton(Composite parent, int id, String label, boolean defaultButton)
	{
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				if (isValidLogin())
				{
					buttonPressed(((Integer) event.widget.getData()).intValue());
				} else
				{
					showLoginError();
				}
			}

		});
		if (defaultButton)
		{
			Shell shell = parent.getShell();
			if (shell != null)
			{
				shell.setDefaultButton(button);
			}
		}
		setButtonLayoutData(button);
		return button;
	}

	private void showLoginError()
	{
		errorLabel.setText("User unknown or password invalid.");

	}

	private boolean isValidLogin()
	{
		String userName = userText.getText().trim();
		String passWord = passwortText.getText().trim();
		if (!uService.setAuthentication(userName, passWord))
		{
			return false;
		}
		BTSQueryRequest query = new BTSQueryRequest();
		query.setQueryBuilder(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userName", userName)));
		List<BTSUser> users = uService.query(query);
		for (BTSUser u : users)
		{
			if (userName.equals(u.getUserName()) && equalsPassword(u, passWord))
			{
				validUser = u;
				return true;
			}
		}
		return false;
	}

	private boolean equalsPassword(BTSUser u, String passWord)
	{
		if (u.getPassword() != null)
		{
			try
			{
				return passWord == StringEncryption.decrypt(null, u.getPassword().getBytes(BTSConstants.ENCODING));
			} catch (UnsupportedEncodingException | GeneralSecurityException e)
			{
				return false;
			}

		}
		return false;
	}

	@Override
	protected void buttonPressed(int buttonId)
	{
		context.set(BTSUIConstants.AUTHENTICATED_USER, validUser);
		super.buttonPressed(buttonId);
	}

	@Override
	public boolean close()
	{
		if (titleImage != null)
		{
			titleImage.dispose();
		}
		return super.close();
	}

	public ImageDescriptor imageDescriptorFromURI(URI iconPath)
	{
		try
		{
			return ImageDescriptor.createFromURL(new URL(iconPath.toString()));
		} catch (MalformedURLException e)
		{
			System.err.println("iconURI \"" + iconPath.toString()
					+ "\" is invalid, a \"missing image\" icon will be shown");
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

}
