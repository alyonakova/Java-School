package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.dto.RegistrationFormDTO;
import ru.t_systems.alyona.sbb.dto.UserDTO;
import ru.t_systems.alyona.sbb.repository.UserRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.UserService;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final PassengerService passengerService;
    private final UserConverter userConverter;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createPassengerUser(RegistrationFormDTO registrationForm) {

        //create passenger
        PassengerDTO passenger = passengerService.createPassenger(registrationForm.getName(),
                registrationForm.getSurname(), registrationForm.getBirthday());

        //create user
        createUser(registrationForm.getLogin(), true, passenger);
    }

    private void createUser(String login, Boolean isPassenger, PassengerDTO passenger) {
        userRepository.create(
                userConverter.userToEntity(new UserDTO(null, login, isPassenger, passenger))
        );
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return userConverter.userToDTO(userRepository.getByLogin(login));
    }
}
