package com.zuehkle.curriculum.gitc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class UsersStorage {

	private static final String STORAGE_FILE_PATH = "/.git_commander/gitcinfig.txt";
	private static final String RECORD_SEPARATOR = ";";
	
	private static File getStorageFile() throws IOException {
		File storageFile = new File(STORAGE_FILE_PATH);
		
		if (!storageFile.exists()) {
			System.out.println(storageFile.getAbsolutePath());
			Files.createDirectories(storageFile.getParentFile().toPath());
			Files.createFile(storageFile.toPath());
		}
		
		return storageFile;
	}
	
	public static Long addNewRegistry(String gitUserName, String gitRepositoryURL){
		long lastId = 1;
		try {
			File storageFile = getStorageFile();
			
			//get last ID
			List<String> lines = FileUtils.readLines(storageFile);
			for (String line : lines) {
				String[] lineRecords = line.split(RECORD_SEPARATOR);

				try {
					long currentId = Long.parseLong(lineRecords[0]);
					if (currentId >= lastId) {
						lastId = currentId + 1;
					}
				} catch (Exception e) {
					continue;
				} 
			}
			
			//insert new line
			lines.add(lastId + RECORD_SEPARATOR 
					+ gitUserName + RECORD_SEPARATOR
					+ gitRepositoryURL);
			FileUtils.writeLines(storageFile, lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lastId;
	}
	
	public static boolean deletRegistry(long registryId) {
		try {
			File storageFile = getStorageFile();
			
			List<String> lines = FileUtils.readLines(storageFile);
			int lineToRemove = -1;
			for (String line : lines) {
				String[] lineRecords = line.split(RECORD_SEPARATOR);

				try {
					long currentId = Long.parseLong(lineRecords[0]);
					if (currentId == registryId) {
						lineToRemove = lines.indexOf(line);
					}
				} catch (Exception e) {
					continue;
				} 
			}
			if (lineToRemove > -1) {
				lines.remove(lineToRemove);
				//update new content
				FileUtils.writeLines(storageFile, lines);
				return true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Map<String, String> getUserRegistry(long registryId) {
		try {
			File storageFile = getStorageFile();
			
			List<String> lines = FileUtils.readLines(storageFile);
			String[] userRegistryStrings = new String[0];
			for (String line : lines) {
				String[] lineRecords = line.split(RECORD_SEPARATOR);

				try {
					long currentId = Long.parseLong(lineRecords[0]);
					if (currentId == registryId) {
						userRegistryStrings = lineRecords;
					}
				} catch (Exception e) {
					continue;
				} 
			}
			if (userRegistryStrings.length > 0) {
				Map<String, String> userRegistry = new LinkedHashMap<String, String>();
				userRegistry.put("gitUser", userRegistryStrings[1]);
				userRegistry.put("gitRepo", userRegistryStrings[2]);
				return userRegistry;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, String> getAllUserRegistries(String userName) {
		Map<String, String> userRegistries = new LinkedHashMap<String, String>();
		try {
			
			File storageFile = getStorageFile();
			
			List<String> lines = FileUtils.readLines(storageFile);
			for (String line : lines) {
				String[] lineRecords = line.split(RECORD_SEPARATOR);

				if (userName.equals(lineRecords[1])) {
					userRegistries.put(lineRecords[0], lineRecords[2]);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userRegistries;
	}
}
