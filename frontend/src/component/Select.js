import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
// import ListItemText from '@mui/material/ListItemText';

export default function BasicSelect({categories}) {
  let array = [];
  let type;
  const [categoryName, setcategoryName] = React.useState('');
  //console.log(categories)
  const handleChange = (event) => {
    console.log(event.target.value)
    const fetchsearchType = async () => {
      const response = await fetch(`http://52.202.27.18:8080/question/java?page=0`);
      
      if (!response.ok) {
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      console.log(data)
      //setcategoryName(data.title);
    };
    fetchsearchType().catch(err => {
      console.log(err);
    });
    setcategoryName(event.target.value);
  };
  categories.map((category) => {
  //console.log(array)
    array.push(category.type);
  })
  type = array.filter(function(v,i){
    return array.indexOf(v) === i;
  });
  //console.log(type);

  return (
    <Box sx={{ mt:1 ,mb:1, width: 250 , backgroundColor: "white"}}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">category</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={categoryName}
          label="category"
          onChange={handleChange}
        > 
          {type.map((item) => {
            //console.log(type)
            return (
              <MenuItem value={item}>{item}</MenuItem>
            )
          })}
        </Select>
      </FormControl>
    </Box>
  );
}