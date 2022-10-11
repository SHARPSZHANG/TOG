import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listAssociation(query) {
  return request({
    url: '/system/association/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getAssociation(id) {
  return request({
    url: '/system/association/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addAssociation(data) {
  return request({
    url: '/system/association',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateAssociation(data) {
  return request({
    url: '/system/association',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delAssociation(id) {
  return request({
    url: '/system/association/' + id,
    method: 'delete'
  })
}
