/**
 */
package org.bbaw.bts.corpus.text.egy.egyDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deletion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bbaw.bts.corpus.text.egy.egyDsl.Deletion#getWChar <em>WChar</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bbaw.bts.corpus.text.egy.egyDsl.EgyDslPackage#getDeletion()
 * @model
 * @generated
 */
public interface Deletion extends Brackets, NoCartouche, NoDisputableReading, NoLacuna, NoExpandedColumn, NoRasur, NoAncientExpanded, NoRestorationOverRasur, NoPartialDestruction
{
  /**
   * Returns the value of the '<em><b>WChar</b></em>' containment reference list.
   * The list contents are of type {@link org.bbaw.bts.corpus.text.egy.egyDsl.NoDeletion}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>WChar</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>WChar</em>' containment reference list.
   * @see org.bbaw.bts.corpus.text.egy.egyDsl.EgyDslPackage#getDeletion_WChar()
   * @model containment="true"
   * @generated
   */
  EList<NoDeletion> getWChar();

} // Deletion
