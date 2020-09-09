package com.webflux.controller;

import com.webflux.bean.PUser;
import com.webflux.service.PUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class PUserController {

    @Autowired
    private PUserService userService;

    @GetMapping(value = "find/{id}")
    public Mono<PUser> findOne(@PathVariable("id") Long id) {
        PUser user = userService.findUserById(id);
        Mono<PUser> mono = Mono.create(monoSink -> monoSink.success(user));
        Mono<PUser> just = Mono.just(user);
        return just;
    }

    @GetMapping("/findAll")
    public Flux<PUser> findAll() {
        List<PUser> list = userService.findUserAll();
        return Flux.create(fluxSink -> {
            list.forEach(city -> {
                fluxSink.next(city);
            });
            fluxSink.complete();
        });

    }

    @PostMapping("/add")
    public Mono<Long> add(@RequestBody PUser city) {
        return Mono.create(monoSink -> monoSink.success(userService.addUser(city)));
    }

    @PostMapping("/update")
    public Mono<Long> update(@RequestBody PUser city) {
        return Mono.create(monoSink -> monoSink.success(userService.updateUser(city)));
    }

    @GetMapping(value = "delete/{id}")
    public Mono<Long> delete(@PathVariable("id") Long id) {
        return Mono.create(monoSink -> monoSink.success(userService.deleteUser(id)));
    }
}