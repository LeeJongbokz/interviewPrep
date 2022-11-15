import Container from '@mui/material/Container';

const ContainerUI = (props) => {
  return (
    <Container sx={{ backgroundColor: "white", height:1, paddingTop : "50px", paddingBottom : "50px"}} >
      {props.children}
    </Container>
  )
}

export default ContainerUI