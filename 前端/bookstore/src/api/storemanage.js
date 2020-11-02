import axios from 'axios';

const instance = axios.create({
  timeout: 10000,
   headers: {
    'Content-Type': "application/json;charset=utf-8"
  }
});

export default {
  FgetById ( data ) {
    return instance.get('book/getById',data);
  },
  FgetByCategory () {
    return instance.get('book/getByCategory');
  },
  FaddBook (data) {
   return instance.post('book/add',data)
  },
  FupdateBook (data) {
    return instance.post('book/update',data)
  },
  FdeleteBook (data){
  	return instance.post('book/delete',data)
  }
};
