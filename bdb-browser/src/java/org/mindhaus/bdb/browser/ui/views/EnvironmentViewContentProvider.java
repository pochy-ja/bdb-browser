/**
 * 
 */
package org.mindhaus.bdb.browser.ui.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.mindhaus.bdb.ClassloaderUtil;
import org.mindhaus.bdb.browser.ui.model.Node;

public class EnvironmentViewContentProvider implements
		IStructuredContentProvider, ITreeContentProvider {

	ClassloaderUtil cLUtil = new ClassloaderUtil();

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}

	public Object getParent(Object child) {
		if (child instanceof Node) {
			return ((Node) child).getParent();
		}
		return null;
	}

	public Object[] getChildren(Object parent) {
		try {
			cLUtil.setClassLoader();
			if (parent instanceof Node) {
				return ((Node) parent).getChildren();
			}

			return new Object[0];
		} finally {
			cLUtil.restoreClassloader();
		}
	}

	public boolean hasChildren(Object parent) {
		if (parent instanceof Node)
			return ((Node) parent).hasChildren();
		return false;
	}
}