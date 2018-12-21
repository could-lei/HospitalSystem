package com.example.demo.unitl;

import com.example.demo.enums.ResultEnum;
import com.example.demo.result.Result;
import org.springframework.validation.BindingResult;

public class ResultUtil {

    public static Result success(){
        return new Result(null, ResultEnum.Success.getCode(), ResultEnum.Success.getMessage());
    }

    public static Result success(Object object){
        return new Result(object, ResultEnum.Success.getCode(), ResultEnum.Success.getMessage());
    }

    public static Result success(Object object, String str){
        return new Result(object, ResultEnum.Success.getCode(),str);
    }

    public static Result fail(){
        return new Result(null, ResultEnum.Fail.getCode(), ResultEnum.Fail.getMessage());
    }

    public static Result fail(String str){
        return new Result(null, ResultEnum.Fail.getCode(),str);
    }

    public static Result error(int i, String 未知错误){//未知错误
        return new Result(null, ResultEnum.Error.getCode(), ResultEnum.Error.getMessage());
    }

    public static Result error(){//未知错误
        return new Result(null, ResultEnum.Error.getCode(),ResultEnum.Error.getMessage());
    }

    public static Result error(BindingResult bindingResult){
        return error(bindingResult.getFieldError().getDefaultMessage());
    }

    public static Result error(String str){//未知错误
        return new Result(null, ResultEnum.Error.getCode(),str);
    }

    public static Result error(Integer code, String info){
        return new Result(null,code,info);
    }
}

