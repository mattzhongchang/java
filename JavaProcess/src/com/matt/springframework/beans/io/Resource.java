package com.matt.springframework.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource��Spring�ڲ�������Դ�Ľӿ�
 * @author Administrator
 *
 */
public interface Resource {

	InputStream getInputStream() throws IOException;
}
