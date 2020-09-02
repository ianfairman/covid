package com.scratch.covidfx;

import javafx.scene.control.Menu;

public class FileMenu extends Menu {

  public FileMenu() {
    super("File");
    getItems().add(new ExitMenuItem());
  }
}
