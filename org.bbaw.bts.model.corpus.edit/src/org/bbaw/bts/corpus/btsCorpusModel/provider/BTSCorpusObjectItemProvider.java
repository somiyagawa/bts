/**
 */
package org.bbaw.bts.corpus.btsCorpusModel.provider;


import java.util.Collection;
import java.util.List;
import org.bbaw.bts.btsmodel.provider.BTSObjectItemProvider;
import org.bbaw.bts.core.commons.staticAccess.StaticAccessController;
import org.bbaw.bts.core.controller.generalController.BTSConfigurationController;
import org.bbaw.bts.corpus.btsCorpusModel.BTSCorpusObject;
import org.bbaw.bts.corpus.btsCorpusModel.BtsCorpusModelFactory;
import org.bbaw.bts.corpus.btsCorpusModel.BtsCorpusModelPackage;
import org.bbaw.bts.ui.commons.utils.BTSUIConstants;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.provider.StyledString.Style;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.TextStyle;

/**
 * This is the item provider adapter for a {@link org.bbaw.bts.corpus.btsCorpusModel.BTSCorpusObject} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BTSCorpusObjectItemProvider
	extends BTSObjectItemProvider {
	
	
	
	protected Styler grey = new Styler() {

		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.foreground = BTSUIConstants.VIEW_FOREGROUND_DESELECTED_COLOR;
			
		}
	};
	
	protected Style GREY = StyledString.Style.newBuilder()
			.setForegroundColor(URI.createURI("color://rgb/200/197/185"))
			.toStyle();
	
	protected Style UNDERLINED = StyledString.Style.newBuilder()
			.setUnderlineColor(URI.createURI("color://rgb/0/0/0"))
			.toStyle();
	
	
	
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BTSCorpusObjectItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addCorpusPrefixPropertyDescriptor(object);
			addWorkPhasePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Corpus Prefix feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCorpusPrefixPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BTSCorpusObject_corpusPrefix_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BTSCorpusObject_corpusPrefix_feature", "_UI_BTSCorpusObject_type"),
				 BtsCorpusModelPackage.Literals.BTS_CORPUS_OBJECT__CORPUS_PREFIX,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Work Phase feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkPhasePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BTSCorpusObject_workPhase_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BTSCorpusObject_workPhase_feature", "_UI_BTSCorpusObject_type"),
				 BtsCorpusModelPackage.Literals.BTS_CORPUS_OBJECT__WORK_PHASE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(BtsCorpusModelPackage.Literals.BTS_CORPUS_OBJECT__PASSPORT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return ((StyledString)getStyledText(object)).getString();
	}

	/**
	 * This returns the label styled text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generatedNOT
	 */
	@Override
	public Object getStyledText(Object object) {
		String label = ((BTSCorpusObject)object).getName();
    	StyledString styledLabel = new StyledString();
		if (label == null || label.length() == 0) {
			styledLabel.append(getString("_UI_BTSCorpusObject_type"), StyledString.Style.QUALIFIER_STYLER); 
		} else {
			styledLabel.append(label, StyledString.Style.QUALIFIER_STYLER);
		}
		styledLabel.append(" [" + ((BTSCorpusObject)object).getProject() + "\\" +  ((BTSCorpusObject)object).getCorpusPrefix() + "]", GREY);

		return styledLabel;
	}
	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(BTSCorpusObject.class)) {
			case BtsCorpusModelPackage.BTS_CORPUS_OBJECT__CORPUS_PREFIX:
			case BtsCorpusModelPackage.BTS_CORPUS_OBJECT__WORK_PHASE:
			case BtsCorpusModelPackage.BTS_TEXT_CORPUS__ACTIVE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case BtsCorpusModelPackage.BTS_CORPUS_OBJECT__PASSPORT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(BtsCorpusModelPackage.Literals.BTS_CORPUS_OBJECT__PASSPORT,
				 BtsCorpusModelFactory.eINSTANCE.createBTSPassport()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return BTSCorpusModelEditPlugin.INSTANCE;
	}

}
