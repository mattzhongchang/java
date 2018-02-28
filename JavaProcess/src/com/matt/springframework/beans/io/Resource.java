package com.matt.springframework.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource是Spring内部定义资源的接口
 * @author Administrator
 *
 */
public interface Resource {

	InputStream getInputStream() throws IOException;
}
