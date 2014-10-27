/*
 * generated by Xtext
 */
package org.bbaw.bts.corpus.text.egy.validation;

import org.bbaw.bts.corpus.text.egy.egyDsl.AncientExpanded;
import org.bbaw.bts.corpus.text.egy.egyDsl.Brackets;
import org.bbaw.bts.corpus.text.egy.egyDsl.Deletion;
import org.bbaw.bts.corpus.text.egy.egyDsl.DisputableReading;
import org.bbaw.bts.corpus.text.egy.egyDsl.EgyDslPackage;
import org.bbaw.bts.corpus.text.egy.egyDsl.Emendation;
import org.bbaw.bts.corpus.text.egy.egyDsl.Expanded;
import org.bbaw.bts.corpus.text.egy.egyDsl.ExpandedColumn;
import org.bbaw.bts.corpus.text.egy.egyDsl.Interfix;
//import org.bbaw.bts.corpus.text.egy.egyDsl.Interfix;
import org.bbaw.bts.corpus.text.egy.egyDsl.Lacuna;
import org.bbaw.bts.corpus.text.egy.egyDsl.PartialDestruction;
import org.bbaw.bts.corpus.text.egy.egyDsl.Rasur;
import org.bbaw.bts.corpus.text.egy.egyDsl.RestorationOverRasur;
import org.bbaw.bts.corpus.text.egy.egyDsl.Word;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.validation.Check;

/**
 * Custom validation rules.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
public class EgyDslJavaValidator extends
		org.bbaw.bts.corpus.text.egy.validation.AbstractEgyDslJavaValidator {

	@Check
	public void checkWordItemsDoNotRepeteThemselves(Word word) {
		if (!word.getWChar().isEmpty()) {
			if (word.getWChar()
					.get(0)
					.getClass()
					.getName()
					.startsWith(
							"org.bbaw.bts.corpus.text.egy.egyDsl.impl.Interfix")) {
				error("Interfix may not be at the beginning of a word!", word,
						EgyDslPackage.Literals.WORD__WCHAR, 0);
			}
			INode node = NodeModelUtils.getNode(word);
			String text = node.getText();
			System.out.println(text);

			if (text.contains("  "))
			{
				int i = text.indexOf("  ");
				error("Only single withespace allowed!", word,
						EgyDslPackage.Literals.WORD__WCHAR, i);
			} else if (text.contains("\n"))
			{
				int i = text.indexOf("\n");
				error("Only linebreak allowed inside a word!", word,
						EgyDslPackage.Literals.WORD__WCHAR, i);
			}else if (text.contains("\r"))
			{
				int i = text.indexOf("\r");
				error("No linebreak allowed inside a word!", word,
						EgyDslPackage.Literals.WORD__WCHAR, i);
			}
			EObject last = null;
			int index = 0;
			for (EObject item : word.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", word,
							EgyDslPackage.Literals.WORD__WCHAR, index - 1);
				}
				last = item;
			}
			if (last instanceof Interfix) {
				error("Interfix may not be at the end of a word!", word,
						EgyDslPackage.Literals.WORD__WCHAR, index - 1);
			} else 
				if (last instanceof Brackets) {
//				if (last instanceof Expanded) {
//					checkBracketContentLastCharNoInterfix((Expanded) last);
//				} else if (last instanceof Emendation) {
//					checkBracketContentLastCharNoInterfix((Emendation) last);
//				} else if (last instanceof DisputableReading) {
//					checkBracketContentLastCharNoInterfix((DisputableReading) last);
//				} else if (last instanceof Lacuna) {
//					checkBracketContentLastCharNoInterfix((Lacuna) last);
//				} else if (last instanceof Deletion) {
//					checkBracketContentLastCharNoInterfix((Deletion) last);
//				} else if (last instanceof ExpandedColumn) {
//					checkBracketContentLastCharNoInterfix((ExpandedColumn) last);
//				} else if (last instanceof Rasur) {
//					checkBracketContentLastCharNoInterfix((Rasur) last);
//				} else if (last instanceof AncientExpanded) {
//					checkBracketContentLastCharNoInterfix((AncientExpanded) last);
//				} else if (last instanceof RestorationOverRasur) {
//					checkBracketContentLastCharNoInterfix((RestorationOverRasur) last);
//				} else if (last instanceof PartialDestruction) {
//					checkBracketContentLastCharNoInterfix((PartialDestruction) last);
//				}
			}
		}
	}

//	private void checkBracketContentLastCharNoInterfix(Expanded bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.EXPANDED__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(Emendation bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.EMENDATION__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(DisputableReading bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(Lacuna bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.LACUNA__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(Deletion bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.DELETION__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(ExpandedColumn bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.EXPANDED_COLUMN__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(Rasur bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.RASUR__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(AncientExpanded bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.ANCIENT_EXPANDED__WCHAR, index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(
//			RestorationOverRasur bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.RESTORATION_OVER_RASUR__WCHAR,
//						index);
//			}
//		}
//	}
//
//	private void checkBracketContentLastCharNoInterfix(
//			PartialDestruction bracket) {
//		int index = bracket.getWChar().size() - 1;
//		if (bracket.getWChar() != null && bracket.getWChar().size() > 0) {
//			EObject lastItem = bracket.getWChar().get(
//					bracket.getWChar().size() - 1);
//			if (lastItem instanceof Interfix) {
//				error("Interfix may not be at the end of a word!", bracket,
//						EgyDslPackage.Literals.PARTIAL_DESTRUCTION__WCHAR,
//						index);
//			}
//		}
//	}

	@Check
	public void checkExpandedItemsDoNotRepeteThemselves(Expanded expanded) {
		if (!expanded.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : expanded.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", expanded,
							EgyDslPackage.Literals.EXPANDED__WCHAR, index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkDisputableReadingItemsDoNotRepeteThemselves(
			DisputableReading bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkEmendationItemsDoNotRepeteThemselves(Emendation bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkLacunaItemsDoNotRepeteThemselves(Lacuna bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkDeletionItemsDoNotRepeteThemselves(Deletion bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkExpandedColumnItemsDoNotRepeteThemselves(
			ExpandedColumn bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkRasurItemsDoNotRepeteThemselves(Rasur bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkAncientExpandedItemsDoNotRepeteThemselves(
			AncientExpanded bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkRestorationOverRasurItemsDoNotRepeteThemselves(
			RestorationOverRasur bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

	@Check
	public void checkPartialDestructionItemsDoNotRepeteThemselves(
			RestorationOverRasur bracket) {
		if (!bracket.getWChar().isEmpty()) {
			String last = "";
			int index = 0;
			for (EObject item : bracket.getWChar()) {
				index++;
				if (item.getClass().getName().equals(last)) {
					error("Items may not repete themselves", bracket,
							EgyDslPackage.Literals.DISPUTABLE_READING__WCHAR,
							index - 1);
				}
				last = item.getClass().getName();
			}
		}
	}

}
