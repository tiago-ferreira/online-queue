package br.com.mio.di.bao.onlinequeue.repository.users;

import br.com.mio.di.bao.onlinequeue.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
