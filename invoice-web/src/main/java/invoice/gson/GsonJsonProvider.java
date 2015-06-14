package invoice.gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Provider
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class GsonJsonProvider<T> implements MessageBodyReader<T>,
		MessageBodyWriter<T> {

	@Override
	public void writeTo(T t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		Gson gson = getGson();
		httpHeaders.get("Content-Type").add("charset=UTF-8");
		entityStream.write(gson.toJson(t).getBytes("UTF-8"));
	}

	@Override
	public long getSize(T t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] antns, MediaType mt) {
		return true;
	}

	@Override
	public T readFrom(Class<T> type, Type genericType, Annotation[] antns,
			MediaType mt, MultivaluedMap<String, String> mm, InputStream in)
			throws IOException, WebApplicationException {
		Gson gson = getGson();
		return gson.fromJson(convertStreamToString(in), type);
	}

	private String convertStreamToString(InputStream inputStream)
			throws IOException {
		if (inputStream != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try (Reader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"))) {
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	private Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		// TODO register type adapters here
		return gsonBuilder.create();
	}

}
