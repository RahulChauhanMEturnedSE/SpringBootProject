package com.codetest.springboot.countApplication.count_application;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("counter")
public class CountController {


    @RequestMapping(value = "/List", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCounterList() {
        if (!LocalStorage.counterStore.isEmpty()) {
            return LocalStorage.counterStore.toString();
        } else {
            return "No counter available";
        }
    }

    @RequestMapping(value = "/value", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCounterValue(@RequestParam(value = "name", required=true) String name) {
        if (LocalStorage.counterStore.containsKey(name)) {
            return LocalStorage.counterStore.get(name).toString();
        } else {
            return "Not Valid Counter";
        }
    }

    @RequestMapping(value = "/createCounter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes  = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createCounter(@RequestBody String newCounter) {
        if (LocalStorage.counterStore.containsKey(newCounter)) {
            return "Counter already exists";
        }
        LocalStorage.counterStore.put(newCounter, 0);
        return "Counter is added succesfully";
    }

    @RequestMapping(value = "/increment/{counter}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String incrementCounter(@RequestParam(value = "name", required=true) String name) {
        if (LocalStorage.counterStore.containsKey(name)) {
            LocalStorage.counterStore.put(name, LocalStorage.counterStore.get(name) +1);
            return "Counter is incremented";
        } else {
            return "No counter available with given name";
        }
    }

}
