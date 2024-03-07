package com.soft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoBean {
    private String name;

    private DemoBeanInner demoBeanInner;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DemoBeanInner {
        private String name;
    }


    public DemoBean(String name) {
        this.name = name;
    }
}
