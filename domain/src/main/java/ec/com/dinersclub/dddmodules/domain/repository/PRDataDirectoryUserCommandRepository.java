package ec.com.dinersclub.dddmodules.domain.repository;

public interface PRDataDirectoryUserCommandRepository {
	<T> T  createUser(String url, T request);
}
