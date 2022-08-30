import styled from 'styled-components';


const H2 = styled.h2`
    text-align: left;
    margin-left: 300px;
`

const HeaderTab = styled.header`
    margin-bottom: 15px;
`


const Header = () => {
    return (
        <HeaderTab>
            <H2>숨터뷰</H2>
        </HeaderTab>
    )
}

export default Header;