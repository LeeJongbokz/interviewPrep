import axios from 'axios';

const url = process.env.REACT_APP_API_BASE_URL;

const get = async (endpoint, params = '') => {
  console.log(`GET 요청 : ${url + endpoint + '/' + params}`);

  return axios.get(
    url + endpoint + '/' + params,
    // jwt 토큰 사용 시 아래 코드 주석 삭제
    {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('userToken')}`,
      },
    }
  );
};

const post = async (endpoint, data) => {
  const bodyData = JSON.stringify(data);
  console.log(`POST 요청 : ${url + endpoint}`);
  console.log(`POST 데이터 : ${bodyData}`);

  return axios.post(
    url + endpoint,
    bodyData,
    // jwt 토큰 사용 시 아래 코드 주석 삭제
    {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('userToken')}`,
      },
    }
  );
};

const getSuggest = async (endpoint, params = '') => {
  console.log(`getSuggest 요청 : ${url + endpoint + '/' + params}`);
  return axios.get(
    url + endpoint + '/' + encodeURIComponent(params),
    // jwt 토큰 사용 시 아래 코드 주석 삭제
    {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('userToken')}`,
      },
    }
  );
};

/*비로그인 사용자의 음식추천 요청 */
const getRecsys = async (endpoint, data) => {
  console.log(`get 요청 : ${url + endpoint}`);
  console.log(`get 데이터 : ${{ ...data }}`);

  return axios.get(
    url + endpoint,
    { params: { ...data } },
    {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('userToken')}`,
      },
    }
  );
};

const put = async (endpoint, data) => {
  const bodyData = JSON.stringify(data);
  console.log(`PUT 요청 : ${url + endpoint}`);
  console.log(`PUT 데이터 : ${bodyData}`);

  return axios.put(
    url + endpoint,
    bodyData,
    // jwt 토큰 사용 시 아래 코드 주석 삭제
    {
      headers: {
        'Content-type': 'application/json',
        Authorization: `Bearer ${sessionStorage.getItem('userToken')}`,
      },
    }
  );
};

const del = async (endpoint, params = '') => {
  console.log(`DELETE 요청 : ${url + endpoint + '/' + params}`);

  return axios.delete(
    +endpoint + '/' + params,
    // jwt 토큰 사용 시 아래 코드 주석 삭제
    {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('userToken')}`,
      },
    }
  );
};

export { get, post, put, getSuggest, getRecsys, del as delete };
