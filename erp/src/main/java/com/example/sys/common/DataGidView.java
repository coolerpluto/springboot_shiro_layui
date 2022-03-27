package com.example.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGidView {
    private Integer code = 0;
    private String msg = "";
    private Long count = 0L;
    private Object data;

    public DataGidView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGidView(Object data) {
        this.data = data;
    }
}
