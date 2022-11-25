import Button from '@mui/material/Button';

const VerticalSquareButton = ({ children, onClick }) => {
  return (
    <Button
      onClick={onClick}
      variant="contained"
      sx={{
        aspectRatio: '1/1',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        margin: 1,
      }}
    >
      {children}
    </Button>
  );
};

export default VerticalSquareButton;
