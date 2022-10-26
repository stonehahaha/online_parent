package com.stone.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author stonestart
 * @create 2022/8/23 - 15:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoneException extends RuntimeException{
    private Integer code;
    private String msg;

}
