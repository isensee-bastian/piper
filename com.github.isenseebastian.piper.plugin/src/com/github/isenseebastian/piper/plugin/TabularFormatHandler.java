package com.github.isenseebastian.piper.plugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;

public class TabularFormatHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (part instanceof ITextEditor) {
				ITextEditor editor = (ITextEditor) part;
				IDocumentProvider documentProvider = editor.getDocumentProvider();
				IDocument document = documentProvider.getDocument(editor.getEditorInput());
				ISelection selection = editor.getSelectionProvider().getSelection();
				if (selection instanceof TextSelection) {
					TextSelection textSelection = (TextSelection) selection;
					String oldText = textSelection.getText();
					String newText = TabularFormatter.INSTANCE.format(oldText);
					document.replace(textSelection.getOffset(), textSelection.getLength(), newText);
				}
			}
		} catch (Exception ex) {
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog.openError(shell, "Piper Error", "Tabular format failed because of an unexpected error:\n" + ex.getMessage());
		}
		return null;
	}
}
