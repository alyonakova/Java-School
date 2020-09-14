package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.converter.TicketConverter;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;
import ru.t_systems.alyona.sbb.repository.TicketRepository;
import ru.t_systems.alyona.sbb.repository.UserRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.UserService;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final PassengerService passengerService;
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PassengerConverter passengerConverter;
    private final TicketConverter ticketConverter;
    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;

    @Override
    @Transactional
    public OperationResultDTO registerUser(RegistrationFormDTO registrationForm) {

        if (userRepository.getByLogin(registrationForm.getLogin()) != null) {
            return OperationResultDTO.error("A user with the specified login already exists.");
        }

        //create or get passenger
        PassengerEntity passengerEntity = passengerRepository.getByNameAndSurnameAndBirthday(
                registrationForm.getName(), registrationForm.getSurname(), registrationForm.getBirthday());

        PassengerDTO passenger;
        if (passengerEntity != null) {
            if (passengerEntity.getUser() != null) {
                return OperationResultDTO.error("A user with specified name, surname and birthday already exists");
            }
            passenger = passengerConverter.toDTO(passengerEntity);
        } else {
            passenger = passengerService.createPassenger(registrationForm.getName(),
                    registrationForm.getSurname(), registrationForm.getBirthday());
        }

        //create user
        try {
            final UserDTO userDTO = new UserDTO(null, registrationForm.getLogin(), true, passenger,
                    passwordEncoder.encode(registrationForm.getUserPassword()));
            userRepository.create(userConverter.toEntity(userDTO));
        } catch (Exception e) {
            log.error("Failed to create a new user", e);
            return OperationResultDTO.technicalError("A technical error occurred, please try again later.");
        }

        return OperationResultDTO.successful("Registration successful. Now you can log in.");
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        UserDTO user = null;
        try {
            user = userConverter.toDTO(userRepository.getByLogin(login));
        } catch (Exception e) {
            log.error("Failed to get user by login", e);
        }
        return user;
    }

    @Override
    public UserDTO getUserById(BigInteger id) {
        UserDTO user = null;
        try {
            user = userConverter.toDTO(userRepository.getById(id));
        } catch (Exception e) {
            log.error("Failed to get user by ID", e);
        }
        return user;
    }

    @Transactional
    public OperationResultDTO updateEmployeeData(ChangeUserDataDTO changeUserDataDTO) {

        if (changeUserDataDTO.getNewLogin() != null) {

            UserEntity existingUser = userRepository.getByLogin(changeUserDataDTO.getNewLogin());
            if (existingUser != null) {
                return OperationResultDTO.error("The specified login is already exists");
            }

            changeLogin(
                    changeUserDataDTO.getNewLogin(),
                    getUserById(changeUserDataDTO.getUserId())
            );
            return OperationResultDTO.successful("Login is successfully updated");

        } else if (changeUserDataDTO.getNewPassword() != null) {

            changePassword(
                    changeUserDataDTO.getNewPassword(),
                    getUserById(changeUserDataDTO.getUserId())
            );

            return OperationResultDTO.successful("Password is successfully updated");
        }
        return OperationResultDTO.error("Nothing to update");
    }

    private void changeLogin(String login, UserDTO user) {
        try {
            userRepository.updateLogin(login, userConverter.toEntity(user));
        } catch (Exception e) {
            log.error("Failed to update user login", e);
        }
    }

    private void changePassword(String password, UserDTO user) {
        try {
            String encodedPassword = passwordEncoder.encode(password);
            userRepository.updatePassword(encodedPassword, userConverter.toEntity(user));
        } catch (Exception e) {
            log.error("Failed to update user password", e);
        }
    }

    @Override
    public MessageDTO displayUnsuccessfulSignIn() {
        return MessageDTO.builder()
                .text("Wrong login or password!")
                .severity(MessageDTO.Severity.ERROR)
                .build();
    }

    @Override
    @Transactional
    public List<TicketDTO> getUserTickets(UserDTO user) {
        List<TicketDTO> userTickets = null;
        try {
            PassengerDTO passenger = user.getPassenger();
            PassengerEntity passengerEntity = passengerConverter.toEntity(passenger);
            userTickets = ticketConverter.toDTOList(ticketRepository.getByPassenger(passengerEntity));
        } catch (Exception e) {
            log.error("Failed to get passenger's tickets", e);
        }
        return userTickets;
    }
}
