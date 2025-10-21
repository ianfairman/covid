package com.scratch.covidfx.menu;

import javafx.scene.control.Menu;

public class FileMenu extends Menu {

  public FileMenu() {
    super("File");
    getItems().add(new ExitMenuItem());
  }
}
