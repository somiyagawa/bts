/**
 */
package org.bbaw.bts.btsviewmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bbaw.bts.btsviewmodel.BtsviewmodelFactory
 * @model kind="package"
 * @generated
 */
public interface BtsviewmodelPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "btsviewmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://btsviewmodel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "btsviewmodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BtsviewmodelPackage eINSTANCE = org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.bbaw.bts.btsviewmodel.impl.TreeNodeWrapperImpl <em>Tree Node Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bbaw.bts.btsviewmodel.impl.TreeNodeWrapperImpl
	 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getTreeNodeWrapper()
	 * @generated
	 */
	int TREE_NODE_WRAPPER = 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER__PARENT = 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER__CHILDREN = 1;

	/**
	 * The feature id for the '<em><b>Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER__OBJECT = 2;

	/**
	 * The feature id for the '<em><b>Property Change Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER__PROPERTY_CHANGE_SUPPORT = 3;

	/**
	 * The feature id for the '<em><b>Children Loaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER__CHILDREN_LOADED = 4;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER__LABEL = 5;

	/**
	 * The feature id for the '<em><b>Parent Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER__PARENT_OBJECT = 6;

	/**
	 * The number of structural features of the '<em>Tree Node Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER_FEATURE_COUNT = 7;

	/**
	 * The operation id for the '<em>Add Property Change Listener</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER___ADD_PROPERTY_CHANGE_LISTENER__PROPERTYCHANGELISTENER = 0;

	/**
	 * The operation id for the '<em>Remove Property Change Listener</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER___REMOVE_PROPERTY_CHANGE_LISTENER__PROPERTYCHANGELISTENER = 1;

	/**
	 * The number of operations of the '<em>Tree Node Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_NODE_WRAPPER_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.bbaw.bts.btsviewmodel.impl.StatusMessageImpl <em>Status Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bbaw.bts.btsviewmodel.impl.StatusMessageImpl
	 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getStatusMessage()
	 * @generated
	 */
	int STATUS_MESSAGE = 1;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_MESSAGE__MESSAGE = 0;

	/**
	 * The feature id for the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_MESSAGE__CREATION_TIME = 1;

	/**
	 * The feature id for the '<em><b>User Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_MESSAGE__USER_ID = 2;

	/**
	 * The feature id for the '<em><b>Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_MESSAGE__MESSAGE_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_MESSAGE__CHILDREN = 4;

	/**
	 * The number of structural features of the '<em>Status Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_MESSAGE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Status Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_MESSAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.bbaw.bts.btsviewmodel.MessageType <em>Message Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bbaw.bts.btsviewmodel.MessageType
	 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getMessageType()
	 * @generated
	 */
	int MESSAGE_TYPE = 2;

	/**
	 * The meta object id for the '<em>BTS Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bbaw.bts.btsmodel.BTSObject
	 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getBTSObject()
	 * @generated
	 */
	int BTS_OBJECT = 3;

	/**
	 * The meta object id for the '<em>Property Change Support</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.beans.PropertyChangeSupport
	 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getPropertyChangeSupport()
	 * @generated
	 */
	int PROPERTY_CHANGE_SUPPORT = 4;

	/**
	 * The meta object id for the '<em>Property Change Listener</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.beans.PropertyChangeListener
	 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getPropertyChangeListener()
	 * @generated
	 */
	int PROPERTY_CHANGE_LISTENER = 5;

	/**
	 * The meta object id for the '<em>Property Change Event</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.beans.PropertyChangeEvent
	 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getPropertyChangeEvent()
	 * @generated
	 */
	int PROPERTY_CHANGE_EVENT = 6;


	/**
	 * Returns the meta object for class '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper <em>Tree Node Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Node Wrapper</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper
	 * @generated
	 */
	EClass getTreeNodeWrapper();

	/**
	 * Returns the meta object for the reference '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getParent()
	 * @see #getTreeNodeWrapper()
	 * @generated
	 */
	EReference getTreeNodeWrapper_Parent();

	/**
	 * Returns the meta object for the reference list '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getChildren()
	 * @see #getTreeNodeWrapper()
	 * @generated
	 */
	EReference getTreeNodeWrapper_Children();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getObject()
	 * @see #getTreeNodeWrapper()
	 * @generated
	 */
	EAttribute getTreeNodeWrapper_Object();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getPropertyChangeSupport <em>Property Change Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property Change Support</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getPropertyChangeSupport()
	 * @see #getTreeNodeWrapper()
	 * @generated
	 */
	EAttribute getTreeNodeWrapper_PropertyChangeSupport();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#isChildrenLoaded <em>Children Loaded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Children Loaded</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#isChildrenLoaded()
	 * @see #getTreeNodeWrapper()
	 * @generated
	 */
	EAttribute getTreeNodeWrapper_ChildrenLoaded();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getLabel()
	 * @see #getTreeNodeWrapper()
	 * @generated
	 */
	EAttribute getTreeNodeWrapper_Label();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getParentObject <em>Parent Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parent Object</em>'.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#getParentObject()
	 * @see #getTreeNodeWrapper()
	 * @generated
	 */
	EAttribute getTreeNodeWrapper_ParentObject();

	/**
	 * Returns the meta object for the '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#addPropertyChangeListener(java.beans.PropertyChangeListener) <em>Add Property Change Listener</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Add Property Change Listener</em>' operation.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 * @generated
	 */
	EOperation getTreeNodeWrapper__AddPropertyChangeListener__PropertyChangeListener();

	/**
	 * Returns the meta object for the '{@link org.bbaw.bts.btsviewmodel.TreeNodeWrapper#removePropertyChangeListener(java.beans.PropertyChangeListener) <em>Remove Property Change Listener</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Remove Property Change Listener</em>' operation.
	 * @see org.bbaw.bts.btsviewmodel.TreeNodeWrapper#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 * @generated
	 */
	EOperation getTreeNodeWrapper__RemovePropertyChangeListener__PropertyChangeListener();

	/**
	 * Returns the meta object for class '{@link org.bbaw.bts.btsviewmodel.StatusMessage <em>Status Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Status Message</em>'.
	 * @see org.bbaw.bts.btsviewmodel.StatusMessage
	 * @generated
	 */
	EClass getStatusMessage();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.StatusMessage#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.bbaw.bts.btsviewmodel.StatusMessage#getMessage()
	 * @see #getStatusMessage()
	 * @generated
	 */
	EAttribute getStatusMessage_Message();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.StatusMessage#getCreationTime <em>Creation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Time</em>'.
	 * @see org.bbaw.bts.btsviewmodel.StatusMessage#getCreationTime()
	 * @see #getStatusMessage()
	 * @generated
	 */
	EAttribute getStatusMessage_CreationTime();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.StatusMessage#getUserId <em>User Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Id</em>'.
	 * @see org.bbaw.bts.btsviewmodel.StatusMessage#getUserId()
	 * @see #getStatusMessage()
	 * @generated
	 */
	EAttribute getStatusMessage_UserId();

	/**
	 * Returns the meta object for the attribute '{@link org.bbaw.bts.btsviewmodel.StatusMessage#getMessageType <em>Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Type</em>'.
	 * @see org.bbaw.bts.btsviewmodel.StatusMessage#getMessageType()
	 * @see #getStatusMessage()
	 * @generated
	 */
	EAttribute getStatusMessage_MessageType();

	/**
	 * Returns the meta object for the reference list '{@link org.bbaw.bts.btsviewmodel.StatusMessage#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see org.bbaw.bts.btsviewmodel.StatusMessage#getChildren()
	 * @see #getStatusMessage()
	 * @generated
	 */
	EReference getStatusMessage_Children();

	/**
	 * Returns the meta object for enum '{@link org.bbaw.bts.btsviewmodel.MessageType <em>Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Message Type</em>'.
	 * @see org.bbaw.bts.btsviewmodel.MessageType
	 * @generated
	 */
	EEnum getMessageType();

	/**
	 * Returns the meta object for data type '{@link org.bbaw.bts.btsmodel.BTSObject <em>BTS Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>BTS Object</em>'.
	 * @see org.bbaw.bts.btsmodel.BTSObject
	 * @model instanceClass="org.bbaw.bts.btsmodel.BTSObject"
	 * @generated
	 */
	EDataType getBTSObject();

	/**
	 * Returns the meta object for data type '{@link java.beans.PropertyChangeSupport <em>Property Change Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Property Change Support</em>'.
	 * @see java.beans.PropertyChangeSupport
	 * @model instanceClass="java.beans.PropertyChangeSupport"
	 * @generated
	 */
	EDataType getPropertyChangeSupport();

	/**
	 * Returns the meta object for data type '{@link java.beans.PropertyChangeListener <em>Property Change Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Property Change Listener</em>'.
	 * @see java.beans.PropertyChangeListener
	 * @model instanceClass="java.beans.PropertyChangeListener"
	 * @generated
	 */
	EDataType getPropertyChangeListener();

	/**
	 * Returns the meta object for data type '{@link java.beans.PropertyChangeEvent <em>Property Change Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Property Change Event</em>'.
	 * @see java.beans.PropertyChangeEvent
	 * @model instanceClass="java.beans.PropertyChangeEvent"
	 * @generated
	 */
	EDataType getPropertyChangeEvent();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BtsviewmodelFactory getBtsviewmodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.bbaw.bts.btsviewmodel.impl.TreeNodeWrapperImpl <em>Tree Node Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bbaw.bts.btsviewmodel.impl.TreeNodeWrapperImpl
		 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getTreeNodeWrapper()
		 * @generated
		 */
		EClass TREE_NODE_WRAPPER = eINSTANCE.getTreeNodeWrapper();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE_NODE_WRAPPER__PARENT = eINSTANCE.getTreeNodeWrapper_Parent();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE_NODE_WRAPPER__CHILDREN = eINSTANCE.getTreeNodeWrapper_Children();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_NODE_WRAPPER__OBJECT = eINSTANCE.getTreeNodeWrapper_Object();

		/**
		 * The meta object literal for the '<em><b>Property Change Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_NODE_WRAPPER__PROPERTY_CHANGE_SUPPORT = eINSTANCE.getTreeNodeWrapper_PropertyChangeSupport();

		/**
		 * The meta object literal for the '<em><b>Children Loaded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_NODE_WRAPPER__CHILDREN_LOADED = eINSTANCE.getTreeNodeWrapper_ChildrenLoaded();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_NODE_WRAPPER__LABEL = eINSTANCE.getTreeNodeWrapper_Label();

		/**
		 * The meta object literal for the '<em><b>Parent Object</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_NODE_WRAPPER__PARENT_OBJECT = eINSTANCE.getTreeNodeWrapper_ParentObject();

		/**
		 * The meta object literal for the '<em><b>Add Property Change Listener</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TREE_NODE_WRAPPER___ADD_PROPERTY_CHANGE_LISTENER__PROPERTYCHANGELISTENER = eINSTANCE.getTreeNodeWrapper__AddPropertyChangeListener__PropertyChangeListener();

		/**
		 * The meta object literal for the '<em><b>Remove Property Change Listener</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TREE_NODE_WRAPPER___REMOVE_PROPERTY_CHANGE_LISTENER__PROPERTYCHANGELISTENER = eINSTANCE.getTreeNodeWrapper__RemovePropertyChangeListener__PropertyChangeListener();

		/**
		 * The meta object literal for the '{@link org.bbaw.bts.btsviewmodel.impl.StatusMessageImpl <em>Status Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bbaw.bts.btsviewmodel.impl.StatusMessageImpl
		 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getStatusMessage()
		 * @generated
		 */
		EClass STATUS_MESSAGE = eINSTANCE.getStatusMessage();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_MESSAGE__MESSAGE = eINSTANCE.getStatusMessage_Message();

		/**
		 * The meta object literal for the '<em><b>Creation Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_MESSAGE__CREATION_TIME = eINSTANCE.getStatusMessage_CreationTime();

		/**
		 * The meta object literal for the '<em><b>User Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_MESSAGE__USER_ID = eINSTANCE.getStatusMessage_UserId();

		/**
		 * The meta object literal for the '<em><b>Message Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_MESSAGE__MESSAGE_TYPE = eINSTANCE.getStatusMessage_MessageType();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATUS_MESSAGE__CHILDREN = eINSTANCE.getStatusMessage_Children();

		/**
		 * The meta object literal for the '{@link org.bbaw.bts.btsviewmodel.MessageType <em>Message Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bbaw.bts.btsviewmodel.MessageType
		 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getMessageType()
		 * @generated
		 */
		EEnum MESSAGE_TYPE = eINSTANCE.getMessageType();

		/**
		 * The meta object literal for the '<em>BTS Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bbaw.bts.btsmodel.BTSObject
		 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getBTSObject()
		 * @generated
		 */
		EDataType BTS_OBJECT = eINSTANCE.getBTSObject();

		/**
		 * The meta object literal for the '<em>Property Change Support</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.beans.PropertyChangeSupport
		 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getPropertyChangeSupport()
		 * @generated
		 */
		EDataType PROPERTY_CHANGE_SUPPORT = eINSTANCE.getPropertyChangeSupport();

		/**
		 * The meta object literal for the '<em>Property Change Listener</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.beans.PropertyChangeListener
		 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getPropertyChangeListener()
		 * @generated
		 */
		EDataType PROPERTY_CHANGE_LISTENER = eINSTANCE.getPropertyChangeListener();

		/**
		 * The meta object literal for the '<em>Property Change Event</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.beans.PropertyChangeEvent
		 * @see org.bbaw.bts.btsviewmodel.impl.BtsviewmodelPackageImpl#getPropertyChangeEvent()
		 * @generated
		 */
		EDataType PROPERTY_CHANGE_EVENT = eINSTANCE.getPropertyChangeEvent();

	}

} //BtsviewmodelPackage
