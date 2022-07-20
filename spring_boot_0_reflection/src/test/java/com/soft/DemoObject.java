package com.soft;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class DemoObject implements Serializable {

    private static final long serialVersionUID = 7402618869265339578L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getNameStr() {
        return "获取到的getNameStr";
    }

}
