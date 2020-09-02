package com.scratch.covidfx;

import javafx.scene.control.MenuBar;

public class CovidMenuBar extends MenuBar {

  public CovidMenuBar() {
    getMenus().add(new FileMenu());
  }
}
