/*******************************************************************************
 * Copyright (c) 2009, 2010 Matthew Hall and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Matthew Hall - initial API and implementation (bug 268472)
 * Matthew Hall - bug 300953
 ******************************************************************************/

package org.bbaw.bts.ui.commons.controldecoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bbaw.bts.ui.commons.controldecoration.support.BackgroundControlDecorationUpdater;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.DisposeEvent;
import org.eclipse.core.databinding.observable.IDecoratingObservable;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiffVisitor;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.databinding.viewers.IViewerObservable;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

/**
 * Decorates the underlying controls of the target observables of a
 * {@link ValidationStatusProvider} with {@link ControlDecoration}s mirroring
 * the current validation status. Only those target observables which implement
 * {@link ISWTObservable} or {@link IViewerObservable} are decorated.
 * 
 * This add coloring the widgets background according to validation status.
 * Invalid = red background
 * valid = white background
 * 
 * @since 1.4
 */
public class BackgroundControlDecorationSupport
{
	/**
	 * Creates a ControlDecorationSupport which observes the validation status
	 * of the specified {@link ValidationStatusProvider}, and displays a
	 * {@link ControlDecoration} over the underlying SWT control of all target
	 * observables that implement {@link ISWTObservable} or
	 * {@link IViewerObservable}.
	 * 
	 * @param validationStatusProvider
	 *            the {@link ValidationStatusProvider} to monitor.
	 * @param position
	 *            SWT alignment constant (e.g. SWT.LEFT | SWT.TOP) to use when
	 *            constructing {@link BackgroundControlDecorationSupport}
	 * @return a ControlDecorationSupport which observes the validation status
	 *         of the specified {@link ValidationStatusProvider}, and displays a
	 *         {@link ControlDecoration} over the underlying SWT control of all
	 *         target observables that implement {@link ISWTObservable} or
	 *         {@link IViewerObservable}.
	 */
	public static BackgroundControlDecorationSupport create(ValidationStatusProvider validationStatusProvider,
			int position)
	{
		return create(validationStatusProvider, position, null, new BackgroundControlDecorationUpdater());
	}

	/**
	 * Creates a ControlDecorationSupport which observes the validation status
	 * of the specified {@link ValidationStatusProvider}, and displays a
	 * {@link ControlDecoration} over the underlying SWT control of all target
	 * observables that implement {@link ISWTObservable} or
	 * {@link IViewerObservable}.
	 * 
	 * @param validationStatusProvider
	 *            the {@link ValidationStatusProvider} to monitor.
	 * @param position
	 *            SWT alignment constant (e.g. SWT.LEFT | SWT.TOP) to use when
	 *            constructing {@link ControlDecoration} instances.
	 * @param composite
	 *            the composite to use when constructing
	 *            {@link ControlDecoration} instances.
	 * @return a ControlDecorationSupport which observes the validation status
	 *         of the specified {@link ValidationStatusProvider}, and displays a
	 *         {@link ControlDecoration} over the underlying SWT control of all
	 *         target observables that implement {@link ISWTObservable} or
	 *         {@link IViewerObservable}.
	 */
	public static BackgroundControlDecorationSupport create(ValidationStatusProvider validationStatusProvider,
			int position, Composite composite)
	{
		return create(validationStatusProvider, position, composite, new BackgroundControlDecorationUpdater());
	}

	/**
	 * Creates a ControlDecorationSupport which observes the validation status
	 * of the specified {@link ValidationStatusProvider}, and displays a
	 * {@link ControlDecoration} over the underlying SWT control of all target
	 * observables that implement {@link ISWTObservable} or
	 * {@link IViewerObservable}.
	 * 
	 * @param validationStatusProvider
	 *            the {@link ValidationStatusProvider} to monitor.
	 * @param position
	 *            SWT alignment constant (e.g. SWT.LEFT | SWT.TOP) to use when
	 *            constructing {@link ControlDecoration} instances.
	 * @param composite
	 *            the composite to use when constructing
	 *            {@link ControlDecoration} instances.
	 * @param updater
	 *            custom strategy for updating the {@link ControlDecoration}(s)
	 *            whenever the validation status changes.
	 * @return a ControlDecorationSupport which observes the validation status
	 *         of the specified {@link ValidationStatusProvider}, and displays a
	 *         {@link ControlDecoration} over the underlying SWT control of all
	 *         target observables that implement {@link ISWTObservable} or
	 *         {@link IViewerObservable}.
	 */
	public static BackgroundControlDecorationSupport create(ValidationStatusProvider validationStatusProvider,
			int position, Composite composite, BackgroundControlDecorationUpdater updater)
	{
		return new BackgroundControlDecorationSupport(validationStatusProvider, position, composite, updater);
	}

	/** The position. */
	private final int position;
	
	/** The composite. */
	private final Composite composite;
	
	/** The updater. */
	private final BackgroundControlDecorationUpdater updater;

	/** The validation status. */
	private IObservableValue validationStatus;
	
	/** The targets. */
	private IObservableList targets;

	/** The dispose listener. */
	private IDisposeListener disposeListener = new IDisposeListener()
	{
		public void handleDispose(DisposeEvent staleEvent)
		{
			dispose();
		}
	};

	/** The status change listener. */
	private IValueChangeListener statusChangeListener = new IValueChangeListener()
	{
		public void handleValueChange(ValueChangeEvent event)
		{
			statusChanged((IStatus) validationStatus.getValue());
		}
	};

	/** The targets change listener. */
	private IListChangeListener targetsChangeListener = new IListChangeListener()
	{
		public void handleListChange(ListChangeEvent event)
		{
			event.diff.accept(new ListDiffVisitor()
			{
				public void handleAdd(int index, Object element)
				{
					targetAdded((IObservable) element);
				}

				public void handleRemove(int index, Object element)
				{
					targetRemoved((IObservable) element);
				}
			});
			statusChanged((IStatus) validationStatus.getValue());
		}
	};

	/**
	 * The Class TargetDecoration.
	 *
	 * @author Christoph Plutte
	 */
	private static class TargetDecoration
	{
		
		/** The target. */
		public final IObservable target;
		
		/** The decoration. */
		public final ControlDecoration decoration;

		/**
		 * Instantiates a new target decoration.
		 *
		 * @param target the target
		 * @param decoration the decoration
		 */
		TargetDecoration(IObservable target, ControlDecoration decoration)
		{
			this.target = target;
			this.decoration = decoration;
		}
	}

	/** The target decorations. */
	private List targetDecorations;

	/**
	 * Instantiates a new background control decoration support.
	 *
	 * @param validationStatusProvider the validation status provider
	 * @param position the position
	 * @param composite the composite
	 * @param updater the updater
	 */
	private BackgroundControlDecorationSupport(ValidationStatusProvider validationStatusProvider, int position,
			Composite composite, BackgroundControlDecorationUpdater updater)
	{
		this.position = position;
		this.composite = composite;
		this.updater = updater;

		this.validationStatus = validationStatusProvider.getValidationStatus();
		Assert.isTrue(!this.validationStatus.isDisposed());

		this.targets = validationStatusProvider.getTargets();
		Assert.isTrue(!this.targets.isDisposed());

		this.targetDecorations = new ArrayList();

		validationStatus.addDisposeListener(disposeListener);
		validationStatus.addValueChangeListener(statusChangeListener);

		targets.addDisposeListener(disposeListener);
		targets.addListChangeListener(targetsChangeListener);

		for (Iterator it = targets.iterator(); it.hasNext();)
			targetAdded((IObservable) it.next());

		statusChanged((IStatus) validationStatus.getValue());
	}

	/**
	 * Target added.
	 *
	 * @param target the target
	 */
	private void targetAdded(IObservable target)
	{
		Control control = findControl(target);
		if (control != null)
		{
			targetDecorations.add(new TargetDecoration(target, new ControlDecoration(control, position, composite)));

		}
	}

	/**
	 * Target removed.
	 *
	 * @param target the target
	 */
	private void targetRemoved(IObservable target)
	{
		for (Iterator it = targetDecorations.iterator(); it.hasNext();)
		{
			TargetDecoration targetDecoration = (TargetDecoration) it.next();
			if (targetDecoration.target == target)
			{
				targetDecoration.decoration.dispose();
				it.remove();
			}
		}
	}

	/**
	 * Find control.
	 *
	 * @param target the target
	 * @return the control
	 */
	private Control findControl(IObservable target)
	{
		if (target instanceof ISWTObservable)
		{
			Widget widget = ((ISWTObservable) target).getWidget();
			if (widget instanceof Control) return (Control) widget;
		}

		if (target instanceof IViewerObservable)
		{
			Viewer viewer = ((IViewerObservable) target).getViewer();
			return viewer.getControl();
		}

		if (target instanceof IDecoratingObservable)
		{
			IObservable decorated = ((IDecoratingObservable) target).getDecorated();
			Control control = findControl(decorated);
			if (control != null) return control;
		}

		if (target instanceof IObserving)
		{
			Object observed = ((IObserving) target).getObserved();
			if (observed instanceof IObservable) return findControl((IObservable) observed);
		}

		return null;
	}

	/**
	 * Status changed.
	 *
	 * @param status the status
	 */
	private void statusChanged(IStatus status)
	{
		for (Iterator it = targets.iterator(); it.hasNext();)
		{
			IObservable observable = (IObservable) it.next();
			updater.updateBackground(findControl(observable), status);
		}
		for (Iterator it = targetDecorations.iterator(); it.hasNext();)
		{
			TargetDecoration targetDecoration = (TargetDecoration) it.next();
			ControlDecoration decoration = targetDecoration.decoration;
			updater.update(decoration, status);
			updater.updateBackground(findControl(targetDecoration.target), status);

		}
	}

	/**
	 * Disposes this ControlDecorationSupport, including all control decorations
	 * managed by it. A ControlDecorationSupport is automatically disposed when
	 * its target ValidationStatusProvider is disposed.
	 */
	public void dispose()
	{
		if (validationStatus != null)
		{
			validationStatus.removeDisposeListener(disposeListener);
			validationStatus.removeValueChangeListener(statusChangeListener);
			validationStatus = null;
		}

		if (targets != null)
		{
			targets.removeDisposeListener(disposeListener);
			targets.removeListChangeListener(targetsChangeListener);
			targets = null;
		}

		disposeListener = null;
		statusChangeListener = null;
		targetsChangeListener = null;

		if (targetDecorations != null)
		{
			for (Iterator it = targetDecorations.iterator(); it.hasNext();)
			{
				TargetDecoration targetDecoration = (TargetDecoration) it.next();
				targetDecoration.decoration.dispose();
			}
			targetDecorations.clear();
			targetDecorations = null;
		}
	}
}
