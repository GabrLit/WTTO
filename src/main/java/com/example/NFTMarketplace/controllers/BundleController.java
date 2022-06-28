package com.example.NFTMarketplace.controllers;

import com.example.NFTMarketplace.commands.BundleCommand;
import com.example.NFTMarketplace.converters.BundleCommandToBundle;
import com.example.NFTMarketplace.model.Bundle;
import com.example.NFTMarketplace.repositiories.BundleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class BundleController {

    private BundleRepository bundleRepository;
    private BundleCommandToBundle bundleCommandToBundle;

    public BundleController(BundleRepository bundleRepository, BundleCommandToBundle bundleCommandToBundle) {
        this.bundleRepository = bundleRepository;
        this.bundleCommandToBundle = bundleCommandToBundle;
    }

    @RequestMapping(value = {"/bundles", "/bundle/list"})
    public String getBundles(Model model) {
        model.addAttribute("bundles", bundleRepository.findAll());
        return "bundle/list";
    }

    @RequestMapping("/bundle/{id}/show")
    public String getBundleDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("bundle", bundleRepository.findById(id).get());
        return "bundle/show";
    }

    @RequestMapping("/bundle/{id}/delete")
    public String deleteBundle(@PathVariable("id") Long id) {
        bundleRepository.deleteById(id);
        return "redirect:/bundles";
    }

    @GetMapping
    @RequestMapping("/bundle/new")
    public String newBundle(Model model){
        model.addAttribute("bundle", new BundleCommand());
        return "bundle/addedit";
    }

    @GetMapping
    @RequestMapping("/bundle/{id}/edit")
    public String editBundle(Model model, @PathVariable("id") Long id){
        model.addAttribute("bundle", bundleRepository.findById(id).get());
        return "bundle/addedit";
    }

    @PostMapping("bundle")
    public String saveOrUpdate(@ModelAttribute BundleCommand command){

        Optional<Bundle> bundleOptional = Optional.ofNullable(bundleRepository.getBundleById(command.getId()));

        if (!bundleOptional.isPresent()) {
            Bundle detachedPublisher = bundleCommandToBundle.convert(command);
            bundleRepository.save(detachedPublisher);
            return "redirect:/bundles";
        } else {
            Bundle editedBundle  = bundleRepository.findById(command.getId()).get();
            editedBundle.setId(command.getId());
            editedBundle.setName(command.getName());
            editedBundle.setAddress(command.getAddress());
            bundleRepository.save(editedBundle);
            return "redirect:/bundles";
        }
    }
}
