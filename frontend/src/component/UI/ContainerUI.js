import Container from '@mui/material/Container';

const ContainerUI = ({children}) => {
  return (
    <Container sx={{ backgroundColor: "white", height:1, paddingTop : "50px", paddingBottom : "50px"}} >
      {children}
    </Container>
  )
}

export default ContainerUI