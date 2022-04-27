package com.albert.godworld.arm.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.*;

@RestController
public abstract class CurdController<T>{

    protected abstract IService<T> service();

    @PostMapping
    public T create(T t){
        service().save(t);
        return t;
    }

    @PutMapping
    public Boolean edit(T t){
        return service().updateById(t);
    }

    @GetMapping("/id/{id}")
    public T getById(@PathVariable("id") Long id){
        return service().getById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id){
        return service().removeById(id);
    }
}
