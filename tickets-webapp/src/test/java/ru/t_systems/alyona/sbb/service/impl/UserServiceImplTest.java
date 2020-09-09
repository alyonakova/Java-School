package ru.t_systems.alyona.sbb.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.converter.TicketConverter;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.entity.TicketEntity;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.TicketRepository;
import ru.t_systems.alyona.sbb.repository.UserRepository;

import java.math.BigInteger;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final BigInteger USER_ID = BigInteger.valueOf(999);
    public static final String LOGIN = "johnny";
    public static final String NEW_LOGIN = "newLogin";

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;
    @Mock
    UserConverter userConverter;
    @Mock
    TicketRepository ticketRepository;
    @Mock
    PassengerConverter passengerConverter;
    @Mock
    TicketConverter ticketConverter;
    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    class getUserByLogin {

        @Test
        void returnsUser_ifUserExistsInRepository() {

            var existingUserEntity = mock(UserEntity.class);
            given(userRepository.getByLogin(LOGIN)).willReturn(existingUserEntity);

            var convertedUser = mock(UserDTO.class);
            given(userConverter.toDTO(existingUserEntity)).willReturn(convertedUser);

            UserDTO user = userService.getUserByLogin(LOGIN);

            assertThat(user).isEqualTo(convertedUser);
        }

        @Test
        void returnsNull_ifUserDoesNotExistInRepository() {
            given(userRepository.getByLogin(LOGIN)).willReturn(null);

            UserDTO user = userService.getUserByLogin(LOGIN);

            assertThat(user).isNull();
        }
    }

    @Nested
    class getUserById {

        @Test
        void returnsNull_ifUserNotFound() {
            given(userRepository.getById(USER_ID)).willReturn(null);

            UserDTO user = userService.getUserById(USER_ID);

            assertThat(user).isNull();
        }

        @Test
        void returnsUser_ifUserExistsInRepository() {
            var existingUserEntity = mock(UserEntity.class);
            given(userRepository.getById(USER_ID)).willReturn(existingUserEntity);

            var existingUserDTO = mock(UserDTO.class);
            given(userConverter.toDTO(existingUserEntity)).willReturn(existingUserDTO);

            UserDTO user = userService.getUserById(USER_ID);

            assertThat(user).isEqualTo(existingUserDTO);
        }
    }

    @Nested
    class updateEmployeeData {

        UserEntity user;
        UserDTO userDTO;
        ChangeUserDataDTO updates;

        public static final String NEW_PASSWORD = "zmiller";
        public static final String ENCRYPTED_PASSWORD = "a1a1a1";

        @BeforeEach
        void initUpdateEmployeeData() {
            user = mock(UserEntity.class);
            given(userRepository.getById(USER_ID)).willReturn(user);

            userDTO = mock(UserDTO.class);
            given(userConverter.toDTO(user)).willReturn(userDTO);
            given(userConverter.toEntity(userDTO)).willReturn(user);

            updates = new ChangeUserDataDTO();
            updates.setUserId(USER_ID);
        }

        @Test
        void changesUserLogin_whenNewLoginIsSpecified() {
            updates.setNewLogin(NEW_LOGIN);

            userService.updateEmployeeData(updates);

            then(userRepository).should().updateLogin(updates.getNewLogin(), user);
        }

        @Test
        void changesUserPassword_whenNewPasswordIsSpecified() {
            updates.setNewPassword(NEW_PASSWORD);
            given(passwordEncoder.encode(NEW_PASSWORD)).willReturn(ENCRYPTED_PASSWORD);

            userService.updateEmployeeData(updates);

            then(userRepository).should().updatePassword(ENCRYPTED_PASSWORD, user);
        }
    }

    @Nested
    class getUserTickets {

        UserDTO user;
        PassengerDTO passengerDTO;
        PassengerEntity passengerEntity;

        @BeforeEach
        void initGetUserTickets() {
            user = mock(UserDTO.class);
            passengerDTO = mock(PassengerDTO.class);
            given(user.getPassenger()).willReturn(passengerDTO);
            passengerEntity = mock(PassengerEntity.class);
            given(passengerConverter.toEntity(passengerDTO)).willReturn(passengerEntity);
        }

        @Test
        void returnsEmptyList_ifTicketsNotFound() {
            given(ticketRepository.getByPassenger(passengerEntity)).willReturn(emptyList());
            given(ticketConverter.toDTOList(emptyList())).willReturn(emptyList());

            List<TicketDTO> tickets = userService.getUserTickets(user);

            assertThat(tickets).isEmpty();
        }

        @Test
        void returnsTicketsList_ifTicketsExist() {
            var ticket = mock(TicketEntity.class);
            given(ticketRepository.getByPassenger(passengerEntity)).willReturn(List.of(ticket));
            var ticketDTO = mock(TicketDTO.class);
            given(ticketConverter.toDTOList(List.of(ticket))).willReturn(List.of(ticketDTO));

            List<TicketDTO> tickets = userService.getUserTickets(user);

            assertThat(tickets).isEqualTo(List.of(ticketDTO));
        }
    }

    @Nested
    class registerUser {

        public static final String EXISTING_LOGIN = "zmiller";

        @Test
        void returnsUnsuccessfulResult_ifLoginExists() {
            RegistrationFormDTO registrationForm = RegistrationFormDTO.builder()
                    .login(EXISTING_LOGIN)
                    .build();
            var userEntity = mock(UserEntity.class);
            given(userRepository.getByLogin(registrationForm.getLogin())).willReturn(userEntity);

            UserRegistrationResultDTO result = userService.registerUser(registrationForm);

            assertThat(result.isSuccessful()).isFalse();

        }
    }

}
