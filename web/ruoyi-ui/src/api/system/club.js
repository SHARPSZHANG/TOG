import request from '@/utils/request'

// 查询社团列表
export function listClub(query) {
  return request({
    url: '/system/club/list',
    method: 'get',
    params: query
  })
}

// 查询社团详细
export function getClub(id) {
  return request({
    url: '/system/club/' + id,
    method: 'get'
  })
}

export function pass(id) {
  return request({
    url: '/system/club/' + id +'/pass',
    method: 'get'
  })
}

// 新增社团
export function addClub(data) {
  return request({
    url: '/system/club',
    method: 'post',
    data: data
  })
}

// 修改社团
export function updateClub(data) {
  return request({
    url: '/system/club',
    method: 'put',
    data: data
  })
}

// 删除社团
export function delClub(id) {
  return request({
    url: '/system/club/' + id,
    method: 'delete'
  })
}
