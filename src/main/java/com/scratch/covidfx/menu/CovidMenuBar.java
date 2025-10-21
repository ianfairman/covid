package com.scratch.covidfx.menu;

import javafx.scene.control.MenuBar;

public class CovidMenuBar extends MenuBar {

  public CovidMenuBar() {
    getMenus().add(new FileMenu());
  }
}
