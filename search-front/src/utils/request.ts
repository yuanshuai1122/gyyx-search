import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse} from 'axios';
import {CONTENT_TYPE} from "../constants";


const axiosCreate: AxiosInstance = axios.create();

axiosCreate.defaults.timeout = 60000;
axiosCreate.defaults.headers.common['Content-Type'] = CONTENT_TYPE.JSON;
axiosCreate.defaults.withCredentials = true;


axiosCreate.interceptors.response.use(
  response => {
    return response;
  },
  (err: any) => {
    if (!err.response) {
      return;
    }
    return Promise.reject(err);
  });

axiosCreate.interceptors.request.use(
  config => config,
  (error: any) => Promise.reject(error),
);

type RequestOpts = Omit<AxiosRequestConfig, 'url'>;

const safeRequest = <T>(url: string, options: RequestOpts, data?: Record<string, unknown>): Promise<T> => {
  return new Promise((resolve, reject) => {
    // 获取登录信息
    const token = localStorage.getItem('token');
    options.headers = {
      ...options.headers,
      'Authorization': token
    };
    axiosCreate({
      method: 'GET',
      ...options,
      url,
      data,
    }).then(
      (res: AxiosResponse<any>) => {
        if (res) {
          resolve(res.data);
        } else {
          reject(res);
        }
      },
      (err: AxiosResponse<any>) => {
        reject(err);
      },
    );
  });
};


/**
 * get
 * @param url
 * @param params
 * @param headers
 * @returns {Promise}
 */
const get = <T>(url: string, params: object = {}, headers = {}): Promise<T> => safeRequest(url, {params, headers});

/**
 * post
 * @param url
 * @param data
 * @param opts
 * @returns {Promise}
 */
const post = <T>(url: string, data = {}, opts: RequestOpts = {}): Promise<T> => {
  return safeRequest(url, {
    data: opts,
    method: 'POST',
  }, data);
};

export default {
  get,
  post,
};
