import React from 'react';
import Container from '../UiElements/Container/Container';
import FooterWrapper, { Text } from './Footer.styled';

const Footer: React.FC<{}> = () => {
  return (
    <FooterWrapper>
      <Container>
        <Text>APDS ©{new Date().getFullYear()} Created by Esc, Org.</Text>
      </Container>
    </FooterWrapper>
  );
};

export default Footer;
