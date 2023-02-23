package br.com.livro.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;

public class RegexUtil {
	private static final Pattern regexAll = Pattern.compile("/politicos");
	private static final Pattern regexById = Pattern
			.compile("/politicos/([0-9]*)");
	// Verificar se a URL � no paddr�o "/politicos/id"
	public static Long matchId(String requestUri) throws ServletException {
		// Verifica o ID
		Matcher matcher = regexById.matcher(requestUri);
		if (matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if (s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
	// Verificar se a URL � no paddr�o "/politicos/id"
	public boolean matchAll(String requestUri) throws ServletException {
		Matcher matcher = regexAll.matcher(requestUri);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}