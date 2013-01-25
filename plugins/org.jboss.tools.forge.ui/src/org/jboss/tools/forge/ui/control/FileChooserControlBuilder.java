/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.tools.forge.ui.control;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.jboss.forge.ui.UIInput;
import org.jboss.forge.ui.hints.InputType;
import org.jboss.forge.ui.hints.InputTypes;
import org.jboss.tools.forge.ui.wizards.ForgeWizardPage;

public class FileChooserControlBuilder extends ControlBuilder
{

   @Override
   public Control build(final ForgeWizardPage page, final UIInput<Object> input, final Composite parent)
   {
      Composite container = new Composite(parent, SWT.NULL);
      container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

      GridLayout layout = new GridLayout();
      container.setLayout(layout);
      layout.numColumns = 2;
      layout.verticalSpacing = 9;
      layout.marginWidth = 0;
      layout.marginHeight = 0;

      final Text containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      containerText.setLayoutData(gd);
      containerText.addModifyListener(new ModifyListener()
      {
         @Override
         public void modifyText(ModifyEvent e)
         {
            String text = containerText.getText();
            if (text != null)
            {
               File file = new File(text);
               setInputValue(input, file);
            }
         }
      });

      Button button = new Button(container, SWT.PUSH);
      button.setText("Browse...");
      button.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            // TODO: Check if it is a Directory or a file selection
            boolean directory = true;
            String selectedPath;
            if (directory)
            {
               DirectoryDialog dialog = new DirectoryDialog(page.getShell(), SWT.OPEN);
               dialog.setText("Select a directory");
               selectedPath = dialog.open();
            }
            else
            {
               FileDialog dialog = new FileDialog(page.getShell(), SWT.OPEN);
               dialog.setText("Select a file");
               selectedPath = dialog.open();
            }
            if (selectedPath != null)
            {
               containerText.setText(selectedPath);

            }
         }
      });
      return container;
   }

   @Override
   protected Class<?> getProducedType()
   {
      return File.class;
   }

   @Override
   protected InputType getSupportedInputType()
   {
      return InputTypes.FILE_PICKER;
   }
}