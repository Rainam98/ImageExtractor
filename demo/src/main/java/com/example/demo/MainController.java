package com.example.demo;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.websocket.server.PathParam;

@RestController
public class MainController {
	
	private String inputFolder = "E:/peronalProjects/ImageExtractor/demo/src/main/webapp/images/";
	private String outputFolder = "C:/Users/raina/OneDrive/Desktop/WeddingAlbum";
	private String userInput;
	File outputFile = new File(outputFolder);

	public String[] imageNames = new String[100000];
	public int globalCounter = 0;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("index");
	}

	@PostMapping("/start")
	public ModelAndView start(Model model, @RequestParam("input") String input) {
		FilenameFilter jpgFilefilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".png")) {
					return true;
				} else {
					return false;
				}
			}
		};
		File directoryPath = new File(inputFolder + input);
		String imageFilesList[] = directoryPath.list(jpgFilefilter);
		userInput = input;
		System.out.println(input);

		for (String fileName : imageFilesList) {
			//System.out.println(fileName);
			imageNames[globalCounter] = fileName;
			globalCounter++;
		}
		
		//System.out.println(counter);
		model.addAttribute("counter", 0);
		model.addAttribute("folderName", input);
		System.out.println(imageNames[(int) model.getAttribute("counter")]);
		model.addAttribute("fileName", imageNames[(int) model.getAttribute("counter")]);
		return new ModelAndView("start");
	}

	@GetMapping("/yes")
	public ModelAndView yes(Model model, @PathParam("counter") int counter) throws Exception {

		// Function to add the file name to the Excel to be added here
		addToFolder(imageNames[counter]);
		counter = counter + 1;
		if (imageNames[counter] != null) {

			model.addAttribute("counter", counter);
			model.addAttribute("folderName", userInput);
			model.addAttribute("fileName", imageNames[counter]);
			
			
			//System.out.println(imageNames[counter] + " Added to Excel");
			return new ModelAndView("start");
		}
		globalCounter = 0;
		return new ModelAndView("end");
	}

	private void addToFolder(String fileName) throws IOException {
		if(!outputFile.exists()) {
			outputFile.mkdir();
		}
		
		File file = new File(inputFolder+"/"+userInput+"/"+fileName);
		File outFile = new File(outputFolder+"/"+userInput+"/"+fileName);
		Files.copy(file.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	@GetMapping("/skip")
	public ModelAndView skip(Model model, @PathParam("counter") int counter) {
		counter = counter + 1;
		//System.out.println(imageNames[counter]);
		if (imageNames[counter] == null) {
			globalCounter = 0;
			return new ModelAndView("end");
		}
		model.addAttribute("counter", counter);
		model.addAttribute("folderName", userInput);
		model.addAttribute("fileName", imageNames[counter]);
		return new ModelAndView("start");
	}
	
	@GetMapping("/prev")
	public ModelAndView prev(Model model, @PathParam("counter") int counter) {
		counter = counter - 1;
		if (counter >= 0 && imageNames[counter] != null) {
			model.addAttribute("counter", counter);
			model.addAttribute("folderName", userInput);
			model.addAttribute("fileName", imageNames[counter]);
			return new ModelAndView("start");
		}
		globalCounter = 0;
		return new ModelAndView("index");
	}
}
