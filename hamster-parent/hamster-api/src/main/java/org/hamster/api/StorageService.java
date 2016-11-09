package org.hamster.api;

public interface StorageService {
	public byte[] getContentAsBytes(long contentId);
	
	public void storeContent(long id, byte[] content);
}
