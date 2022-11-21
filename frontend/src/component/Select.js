import { useState } from 'react';
import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function BasicSelect({categories, onSelect, searchtype}) {
  const [categoryName, setcategoryName] = useState('');
  let array = [];
  let type;

  //category type 받아옴.(새로운 문제 type을 추가하더라도 코드 수정 없도록 하기 위해 데이터를 받아오는 형식으로 함)
  categories.map((category) => {
    array.push(category.type);
  })
  type = array.filter(function(v,i){
    return array.indexOf(v) === i;
  });

  const handleChange = (event) => {
    const target = event.target.value;
    setcategoryName(target);
    //해당 카테고리를 클릭했을 때 해당 카테고리의 type을 onSelect의 파라미터로 가져가서 카테고리 값을 업데이트 시킴 
    onSelect(target);

  };

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
          {type.map((item,index) => {
            //console.log(searchtype)
            return (
              <MenuItem 
                key={index} 
                value={item}
              >{item}</MenuItem>
            )
          })}
        </Select>
      </FormControl>
    </Box>
  );
}