import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <HeaderTab>
            <H2><Link style={{  fontSize:"25px",textDecoration: 'inherit'}}  to="/">숨터뷰</Link></H2>
        </HeaderTab>
    )
}

const H2 = styled.h2`
    text-align: left;
    margin-left: 300px;
`

const HeaderTab = styled.header`
    margin-bottom: 15px;
`
export default Header;