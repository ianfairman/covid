package com.scratch.covidfx;

import javafx.application.Platform;
import javafx.scene.control.MenuItem;

public class ExitMenuItem extends MenuItem {

  public ExitMenuItem() {
    super("Exit");
    setOnAction(e -> {
      Platform.exit();
      System.exit(0);
    });
  }
}
