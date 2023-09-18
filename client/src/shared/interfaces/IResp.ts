import { AxiosHeaders } from "axios";



export interface IResp<T> {
    config : any
    data: IData<T>;
    headers: AxiosHeaders;
    request: XMLHttpRequest
    status: number;
    statusText: string;
}


export interface IData<T> {
    content: T[];
    first: boolean,
    empty: boolean,
    last: boolean,
    number: number,
    numberOfElements: number,
    pageable: {
        pageNumber: number,
        pageSize: number,
        sort: {
            sorted: boolean,
            unsorted: boolean,
            empty: boolean
        },
        offset: number,
        paged: boolean,
        unpaged: boolean
    },
    size: number,
    sort: {
        sorted: boolean,
        unsorted: boolean,
        empty: boolean
    },
    totalElements: number,
    totalPages: number,
}