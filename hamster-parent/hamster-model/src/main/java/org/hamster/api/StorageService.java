package org.hamster.api;

import org.hamster.model.runtime.Content;

public interface StorageService {
	public byte[] getContentAsBytes(Content content);
	
	public void storeContent(Content content);
}
