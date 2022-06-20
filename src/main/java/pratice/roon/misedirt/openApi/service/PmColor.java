package pratice.roon.misedirt.openApi.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PmColor {
    BLUE("text-primary"), GREEN("text-success"), YELLOW("text-warning"), RED("text-danger"), UNKNOWN("");

    private String miseFontColor;

    public String getValue() {
        return this.miseFontColor;
    }
}
