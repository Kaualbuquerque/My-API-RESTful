package me.dio.my_api.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import static java.util.Optional.ofNullable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dio.my_api.domain.model.User;
import me.dio.my_api.domain.repository.UserRepository;
import me.dio.my_api.service.UserService;
import me.dio.my_api.service.exception.BusinessException;

@Service
public class UserServiceImpl implements UserService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public User create(User user) {
        ofNullable(user).orElseThrow(() -> new BusinessException("User to create must be not null!"));
        ofNullable(user.getAccount()).orElseThrow(() -> new BusinessException("User account must not be null!"));
        ofNullable(user.getCard()).orElseThrow(() -> new BusinessException("User card must not be null!"));

        this.validateChangeableId(user.getId(), "created");
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new BusinessException("This account number already exists!");
        }
        if (userRepository.existsByCardNumber(user.getCard().getNumber())) {
            throw new BusinessException("This card number already exists!");
        }

        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User entity) {
        this.validateChangeableId(id, "updated");
        User dbUser = this.findById(id);

        if (!dbUser.getId().equals(entity.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(entity.getName());
        dbUser.setAccount(entity.getAccount());
        dbUser.setCard(entity.getCard());
        dbUser.setFeatures(entity.getFeatures());
        dbUser.setNews(entity.getNews());

        return this.userRepository.save(dbUser);
    }

    @Override
    public void delete(Long id) {
        this.validateChangeableId(id, "Deleted");
        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }

}
