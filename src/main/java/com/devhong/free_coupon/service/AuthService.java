package com.devhong.free_coupon.service;

import com.devhong.free_coupon.dto.Auth;
import com.devhong.free_coupon.exception.CustomErrorCode;
import com.devhong.free_coupon.exception.CustomException;
import com.devhong.free_coupon.model.Client;
import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.model.User;
import com.devhong.free_coupon.repository.PartnerRepository;
import com.devhong.free_coupon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PartnerRepository partnerRepository;
    private final PasswordEncoder passwordEncoder;

    /*
        회원 등록
        - register 메소드 오버로딩
        1. client가 요청보낸 회원가입 정보를 가져와서 이미 가입된 name인지 확인
        2. 이미 있는 name이면 예외발생, mobileNumber도 중복체크
        3. 중복이 없으면 비밀번호는 암호화 처리 후 setPassword
        4. SignUpDto 객체를 Entity객체로 변환 후 DB에 저장(회원가입 완료)
     */
    @Transactional
    public User register(Auth.SignUpUser request) {
        if (userRepository.existsByNicknameOrMobileNumber(request.getNickname(), request.getMobileNumber())) {
            throw new CustomException(CustomErrorCode.NAME_OR_MOBILE_NUMBER_ALREADY_EXISTS);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(request.toEntity());
    }

    @Transactional
    public Partner register(Auth.SignUpPartner request) {
        if (partnerRepository.existsByNicknameOrBusinessNumber(request.getNickname(), request.getBusinessNumber())) {
            throw new CustomException(CustomErrorCode.NAME_OR_BUSINESS_NUMBER_ALREADY_EXISTS);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return partnerRepository.save(request.toEntity());
    }


    /*
        로그인
        - userType으로 파트너회원, 일반유저 구분
        1. client에서 보내온 로그인 요청을 가져와서 아이디가 존재하는지, 비밀번호는 일치하는지 확인
        2. 아이디있고 비밀번호 일치하면 Client 객체 리턴
     */
    public Client authenticate(Auth.SignIn request){
        Client client;

        if (request.getUserType().equals("user")){
            client = userRepository.findByNickname(request.getNickname())
                    .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        }else{
            client = partnerRepository.findByNickname(request.getNickname())
                    .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        }

        if (!passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            throw new CustomException(CustomErrorCode.PASSWORD_NOT_MATCH);
        }

        return client;

    }

    /*
        jwt 토큰에 포함되어있는 username_userType 으로 회원 구분하고 UserDetails 객체 리턴
     */
    @Override
    public UserDetails loadUserByUsername(String client) throws UsernameNotFoundException {
        String clientName = client.split("_")[0];
        String clientType = client.split("_")[1];

        if (clientType.equals("user")){
            return userRepository.findByNickname(clientName)
                    .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        }else{
            return partnerRepository.findByNickname(clientName)
                    .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        }
    }
}
