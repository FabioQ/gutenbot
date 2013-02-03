package gutenbot.gutenbot.parser;

/**
 * Created with IntelliJ IDEA.
 * User: diegocastronuovo
 * Date: 03/02/13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public class ArticleNotFoundException extends Throwable {

    private String errorDescription;

    public ArticleNotFoundException(String s) {
         errorDescription=s;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
