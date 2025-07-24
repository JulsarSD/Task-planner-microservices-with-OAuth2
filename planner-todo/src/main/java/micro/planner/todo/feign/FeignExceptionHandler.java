package micro.planner.todo.feign;

import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

@Component
public class FeignExceptionHandler implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        if (response.status() == 406) {
            return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, readMessage(response));
        }
        return defaultDecoder.decode(s, response);
    }

    private String readMessage(Response response) {

        String message = null;

        try (Reader reader = response.body().asReader(Charset.defaultCharset())) {
            message = CharStreams.toString(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
